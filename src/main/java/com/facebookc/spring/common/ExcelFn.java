package com.facebookc.spring.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ExcelFn {
	
	public Map<String, Object> fnGetExcelToListMap(CommonsMultipartFile pFile, String cellInfo) throws Exception {
		
		Map<String, Object> result = new HashMap<String,Object>();
				
		String fileName = pFile.getOriginalFilename();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(cellInfo);
		JSONArray jsonColsNumberArray = (JSONArray) jsonObject.get("useColsNumber");
		JSONArray jsonColsFieldArray = (JSONArray) jsonObject.get("useColsField");

		String message = "";
		// 예외처리
		if (jsonColsFieldArray.size() == 0) {
			//throw new IllegalAccessError("useColsField의 의 값이 없습니다.");
			message="useColsField의 의 값이 없습니다.";
			result.put("code", "1");
			result.put("data", message);
			return result; 
		}

		if (jsonColsNumberArray.size() == 0) {
			//throw new IllegalAccessError("useColsNumber의 값이 없습니다.");
			message="useColsNumber의 값이 없습니다.";
			result.put("code", "1");
			result.put("data", message);
			return result; 
		}

		if (jsonColsFieldArray.size() != jsonColsNumberArray.size()) {
			//throw new IllegalAccessError("useColsNumber과 useColsField의 사이즈가 일치하지 않습니다.");
			message="useColsNumber과 useColsField의 사이즈가 일치하지 않습니다.";
			result.put("code", "1");
			result.put("data", message);
			return result; 
		}

		Workbook tempWorkbook;
		if (fileName.endsWith("xls")) {
			tempWorkbook = new HSSFWorkbook(pFile.getInputStream());
		} else if (fileName.endsWith("xlsx")) {
			tempWorkbook = new XSSFWorkbook(pFile.getInputStream());
		} else {
			//throw new IllegalAccessError("Excel 파일(xls, xlsx)만 가능합니다.");
			message="Excel 파일(xls, xlsx)만 가능합니다.";
			result.put("code", "1");
			result.put("data", message);
			return result; 
		}

		List<Map<String, Object>> excelDatas = new ArrayList<>();

		try {
			Sheet sheet = tempWorkbook.getSheetAt(0);
			
			FormulaEvaluator evaluator = tempWorkbook.getCreationHelper().createFormulaEvaluator();

			int iRow, iCol, iStartRow;

			// jsonObject 에 담긴 startRow 는 디버깅을 해보면 Long 형식이다. 그래서 int형으로 바꿔서 한다.
			iStartRow = (int) ((long) jsonObject.get("startRow"));

			for (iRow = iStartRow; iRow < sheet.getPhysicalNumberOfRows(); iRow++) {
				Row row = sheet.getRow(iRow);
				
				Map<String, Object> excelData = new HashMap<>();
				String cellString = "";

				excelData.clear();

				for (iCol = 0; iCol < jsonColsNumberArray.size(); iCol++) {
					String value = null;
					Cell cell = null;
					cell = row.getCell(iCol);
					
					if (cell != null) {
						value = "";
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							value = "" + cell.getNumericCellValue();
							break;
						case Cell.CELL_TYPE_STRING:
							value = "" + cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_BLANK:
							//value = "[null 아닌 공백]";
							value = "";
							break;
						case Cell.CELL_TYPE_ERROR:
							value = "" + cell.getErrorCellValue();
							break;
						default:
						}
					} 

					if(value==null){
						value = "";
					}
					
					excelData.put((String) jsonColsFieldArray.get(iCol), value.replaceAll("\\s+$", ""));
					
				}
				excelDatas.add(excelData);
				
			}
			
			result.put("code", "0");
			result.put("data", excelDatas);
			
		} catch (Exception e) {
			result.put("code", "1");
			result.put("data", "");
		}
		
		return result;
	}
}
