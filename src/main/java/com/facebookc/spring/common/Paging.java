package com.facebookc.spring.common;

public class Paging {

	/**
	 * <p>페이징 처리</p>
	 *
	 *  int page 현재페이지<br>
	 *  int totalCount 전체페이지<br>
	 *  int countList 페이지당 보여질 갯수<br>
	 *  int countPage 페이지 리스트 갯수<br>
	 * <br>
	 * @param
	 * @return
	 * @exception 예외사항한 라인에 하나씩
	 */
    public String pagingList(int  page , int totalCount, int countList, int countPage) {
 
    	String result = "";
    	
    	 int totalPage = totalCount / countList;

         if (totalCount % countList > 0) {
             totalPage++;
         }

         if (totalPage < page) {
             page = totalPage;
         }

         int startPage = ((page - 1) / 10) * 10 + 1;
         int endPage = startPage + countPage -1;

         if (endPage > totalPage) {
             endPage = totalPage;
         }

         if (startPage > 1) {
//        	 result += "<a href=\"?page=1\">처음</a>";
        	 result += "<li class='page-first'><a href='?page=1'><i class='fa fa-angle-double-left'></i></a></li>";
        	 
         }

         if (page > 1) {
//        	 result += "<a href=\"?page=" + (page - 1)  + "\">이전</a>";
        	 if(startPage - 1 == 0){
        		 result += "<li class='page-pre'><a href='?page=1'><</a></li>";
        	 }else{
        		 result += "<li class='page-pre'><a href='?page="+(startPage - 1)+"'><</a></li>";
        	 }
        	 
         }

         for (int iCount = startPage; iCount <= endPage; iCount++) {
             if (iCount == page) {
//            	 result += " <b>" + iCount + "</b>";
            	 result += "<li class='page-number active'><a href=''>" + iCount + "</a></li>";
            	 
             } else {
//            	 result += "<a href=\"?page=" + iCount+"\">"+iCount+"</a>";
            	 result += "<li class='page-number'><a href='?page=" + iCount+"'>" + iCount + "</a></li>";
             }
         }

         if (page < totalPage) {
//        	 result += "<a href=\"?page=" + (page + 1)  + "\">다음</a>";
        	 if (endPage < totalPage) {
        		 result += "<li class='page-next'><a href='?page="+ (endPage +1)+"'>></a></li>";
        	 }else{
        		 result += "<li class='page-next'><a href='?page="+ (endPage)+"'>></a></li>";
        	 }
        	 
         }

         if (endPage < totalPage) {
//        	 result += "<a href=\"?page=" + totalPage + "\">끝</a>";
        	 result += "<li class='page-last'><a href='?page="+(totalPage)+"'><i class='fa fa-angle-double-right' aria-hidden='true'></i></a></li>";
        	 
         }
    	
    	return result;
    	
    }
    
    public String pagingList(int  page , int totalCount, int countList, int countPage, String param) {
    	 
    	String result = "";
    	
    	 int totalPage = totalCount / countList;

         if (totalCount % countList > 0) {
             totalPage++;
         }

         if (totalPage < page) {
             page = totalPage;
         }

         int startPage = ((page - 1) / 10) * 10 + 1;
         int endPage = startPage + countPage -1;

         if (endPage > totalPage) {
             endPage = totalPage;
         }

         if (startPage > 1) {
//        	 result += "<a href=\"?page=1\">처음</a>";
        	 result += "<li class='page-first'><a href='?page=1'><i class='fa fa-angle-double-left'></i></a></li>";
        	 
         }

         if (page > 1) {
//        	 result += "<a href=\"?page=" + (page - 1)  + "\">이전</a>";
        	 if(startPage - 1 == 0){
        		 result += "<li class='page-pre'><a href='?page=1"+param+"'><</a></li>";
        	 }else{
        		 result += "<li class='page-pre'><a href='?page="+(startPage - 1)+param+"'><</a></li>";
        	 }
        	 
         }

         for (int iCount = startPage; iCount <= endPage; iCount++) {
             if (iCount == page) {
//            	 result += " <b>" + iCount + "</b>";
            	 result += "<li class='page-number active'><a href=''>" + iCount + "</a></li>";
            	 
             } else {
//            	 result += "<a href=\"?page=" + iCount+"\">"+iCount+"</a>";
            	 result += "<li class='page-number'><a href='?page=" + iCount+param+"'>" + iCount + "</a></li>";
             }
         }

         if (page < totalPage) {
//        	 result += "<a href=\"?page=" + (page + 1)  + "\">다음</a>";
        	 if (endPage < totalPage) {
        		 result += "<li class='page-next'><a href='?page="+ (endPage +1)+param+"'>></a></li>";
        	 }else{
        		 result += "<li class='page-next'><a href='?page="+ (endPage)+param+"'>></a></li>";
        	 }
        	 
         }

         if (endPage < totalPage) {
//        	 result += "<a href=\"?page=" + totalPage + "\">끝</a>";
        	 result += "<li class='page-last'><a href='?page="+(totalPage)+param+"'><i class='fa fa-angle-double-right' aria-hidden='true'></i></a></li>";
        	 
         }
    	
    	return result;
    	
    }
    
}