<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form action="/member/do/register" class="form-signin" role="form" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        	<p>${Request.MESSAGE?if_exists}</p>
			<div class="form-group has-success has-feedback">
				<input type="text" name="loginAcc" class="form-control" placeholder="请输入登录账号" autofocus>
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
		  <div class="form-group has-success has-feedback">
			<input type="text" name="loginPwd" class="form-control" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" name="nickName" class="form-control" placeholder="请输入昵称" style="margin-top:10px;">
			<span class="glyphicon glyphicon glyphicon-tags form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" name="company">
                <option value="1">企业</option>
                <option value="0">个人</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="/member/toLoginPage">我有账号</a>
          </label>
        </div>
        <button class="btn btn-lg btn-success btn-block">注册</button>
      </form>
    </div>
    <script src="/jquery/jquery-2.1.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>