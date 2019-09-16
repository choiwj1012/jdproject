<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/resources/js/auth/register.js" type="text/javascript"></script>
<style>
fieldset {
	border-bottom: solid 1px #e0dfdf;
	margin-bottom: 20px;
}
</style>
<div class="content">
	<div class="container" style="padding-bottom: 50px;">
		<div class="row">
			<div class="col-md-10 col-sm-10 col-md-offset-1 col-sm-offset-1">
				<div class="card">
					<form id="registerForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
						<div class="content">
							<div class="header" style="padding: 0px;">
								<h3 class="title" style="font-weight: bold; font-size: 23px; border-bottom: 1px solid #bbbbbb; padding-bottom: 15px;">회원가입</h3>
							</div>
							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 아이디 <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-5">
										<input class="form-control" type="text" id="email" email="true">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 비밀번호 <b style="color: red; vertical-align: middle;">*</b>
									</label>
									<div class="col-sm-5">
										<input class="form-control" type="password" id="pwd" maxlength="12">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 비밀번호 확인 <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-5">
										<input class="form-control" type="password" id="pwdrepeat" maxlength="12">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 이름 (실명) <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-3">
										<input class="form-control" type="text" id="name" maxlength="5">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 전화번호 <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" id="phone">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 투자경력 <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-2">
										<input class="form-control" type="text" id="career">
									</div>
								</div>
							</fieldset>

							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 control-label"> 투자예상금액 <b style="color: red; vertical-align: middle;">*</b></label>
									<div class="col-sm-4">
										<input class="form-control" type="text" id="amount" style="float: left; width: 90%; text-align: right;">
										<div style="float: right; line-height: 40px;">원</div>
									</div>
								</div>
							</fieldset>

						</div>
						<div class="card-footer text-center">
							<button type="button" id="register" class="btn btn-info btn-fill">가입하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>