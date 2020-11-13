<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${basePath}/view/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath}/view/css/font-awesome.min.css">
	<link rel="stylesheet" href="${basePath}/view/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">CRM-System</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					退出登录
				  </button>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="${basePath}/view/main.jsp"><i class="glyphicon glyphicon-dashboard"></i> 活动分析</a> 
					</li>
					<li class="list-group-item ">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 信息维护<span class="badge" style="float:right">1</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="user.jsp" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
						</ul>
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" onclick="deleteUsers()" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='/DispatcherServlet?method=userAddPage'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
 		  <b>性别： 1表示男，2表示女，0表示未知</b>
          <div class="table-responsive">
            <form id="userForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allSelBox"></th>
                  <th>用户标识</th>
                  <th>用户昵称</th>
                  <th>性别</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
	              <tbody id="userData">	
		              <c:forEach items="${users}" var="user">
		              	<tr>
		              		<td>${user.id}</td>
		              		<td width="30"><input type="checkbox" id="allSelBox"></td>
		              		<td>${user.openid}</td>
		              		<td>${user.nickname}</td>
		              		<td>${user.sex}</td>
		              	<%-- 	<td>${l.getHeadimgurl()}</td> --%>
		              		<td>
    							<button type='button' onclick='goUpdatePage("+user.id+")' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>
    						</td>
		              	</tr>
		              </c:forEach>
	              </tbody>
			  <tfoot>
			     <tr >
					<td colspan="6" align="center">
			            <a href="UserServlet?start=0">[首  页]</a>
			            <a href="UserServlet?start=${pre}">[上一页]</a>
			            <a href="UserServlet?start=${next}">[下一页]</a>
			            <a href="UserServlet?start=${last}">[末  页]</a>
			        </td>
				 </tr>
			  </tfoot>
            </table>
           </form>  
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="script/docs.min.js"></script>
	<script src="layer/layer.js"></script>
   </body>
</html>