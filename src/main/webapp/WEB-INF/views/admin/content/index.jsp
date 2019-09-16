<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="/resources/js/admin/index.js" type="text/javascript"></script>

<style>
table {
	border: 1px solid #ccc;
	width: 100%;
	margin: 0;
	padding: 0;
	border-collapse: collapse;
	border-spacing: 0;
}

table tr {
	/* border: 1px solid #ddd; */
	padding: 5px;
	border: 2px solid #b5b5b5;
}

table th, table td {
	padding: 10px;
	/* text-align: center; */
}

table th {
	text-transform: uppercase;
	font-size: 14px;
	letter-spacing: 1px;
}

@media screen and (max-width: 600px) {
	table {
		border: 0;
	}
	table thead {
		display: none;
	}
	table tr {
		margin-bottom: 10px;
		display: block;
		/* border-bottom: 2px solid #ddd; */
	}
	table td {
		display: block;
		/*   text-align: right; */
		font-size: 13px;
		border-bottom: 1px dotted #ccc;
		word-break: break-all;
		text-align: center;
	}
	table td:last-child {
		border-bottom: 0;
	}
}

.blind {
	background-color: #eee;
}

.blindTd {
	text-decoration: line-through;
}

.tb tr:hover {
	background-color: #dedede !important;
}
</style>

<div class="main-content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card" style="min-height: 640px;">
					<div class="header">
						<p class="category" style="color: #000;">지난메시지</p>
					</div>
					<div class="content">
						<table class="table table-bigboy" style="table-layout: fixed;">
							<thead>
								<tr>
									<th class="text-center" style="width: 8%;">번호</th>
									<th class="text-center" style="width: 15%;">전화번호</th>
									<th class="text-center" style="width: 12%;">이름</th>
									<th class="text-center" style="width: 20%;">신청일자</th>
									<th class="text-center" style="width: 65%;">신청경로</th>
								</tr>
							</thead>
							<tbody class="tb">
								<c:forEach items="${urlContentList}" var="UlContent">
									<tr idx="${UlContent.idx}">
										<td class="text-center">${UlContent.idx}</td>
										<td class="text-center">${UlContent.phone}</td>
										<td class="text-center">${UlContent.name}</td>
										<td class="text-center">${UlContent.regdate}</td>
										<td class="text-left">${UlContent.ref}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div style="text-align: center;">
							<ul class="pagination">${pagingStr}
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>