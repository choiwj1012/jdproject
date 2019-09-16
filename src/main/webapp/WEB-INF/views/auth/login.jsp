<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/resources/js/auth/auth.js" type="text/javascript"></script>

<div class="content">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-sm-6 col-md-offset-3 col-sm-offset-3">
				<form id="aForm">
					<div class="card" data-background="color" data-color="blue">
						<div class="header">
							<h3 class="title"
								style="font-weight: bold; font-size: 23px; border-bottom: 1px solid #bbbbbb; padding-bottom: 15px;">로그인</h3>
						</div>
						<div class="content">
							<div class="form-group">
								<label>아이디</label> <input type="text" id="userId"
									placeholder="Id" class="form-control input-no-border" maxlength="11">
							</div>
							<div class="form-group">
								<label>비밀번호</label> <input type="password" id="pwd"
									placeholder="Password" class="form-control input-no-border" maxlength="12">
							</div>
							<!-- <div class="form-group row" style="text-align: center;">
								<label class="inline"> <input type="checkbox"
									id="rememberChk" /> <span id="remember"> Remember Me</span>
								</label>
							</div> -->
						</div>
						<div class="footer text-center" style="padding: 20px 15px 0px;">
							<!-- <button type="button" id="register" class="btn btn-fill btn-wd ">Register</button> -->
							<button type="button" id="login" class="btn btn-fill btn-wd ">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>