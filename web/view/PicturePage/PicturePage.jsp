<%@page pageEncoding="UTF-8"%>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/main.css">
	<style>
		.tree li {
	        list-style-type: none;
			cursor:pointer;
		}
		.tree-closed {
		    height : 40px;
		}
		.tree-expanded {
		    height : auto;
		}
		.placeholders{
			background-color:#EFEFEF;
		}
		.placeholder img{
			border-radius: 0;
			width:500px;
			height:500px;
		}
		.paging_ul{
			display:inline;
		}
		.paging_ul li{
			display:inline;
			width:10px;
			height:10px;
			border: 1px solid #999999;
			background-color:#EFEFEF;
			margin-right: 10px;
		    padding: 4px;
		    padding-left: 8px;
		    padding-right: 8px;
		}
		.paging_ul li:hover{
			cursor: default;
			border: 1px solid #000000;
		}
		.text{
			width:30px;
			margin-right: 10px;
		}
	}
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
					<i class="glyphicon glyphicon-user"></i> ${loginUser.username}<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="DispatcherServlet?method=loginPage"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="查询">
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
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 活动分析</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理 <span class="badge" style="float:right">5</span></span> 
						<ul style="margin-top:10px;">
						
							<li style="height:30px;">
								<a href="user.html"><i class="glyphicon glyphicon-user"></i> 信息维护</a> 
							</li>
							<li style="height:30px;">
								<a href="PicturePage.jsp" style="color:red;"><i class="glyphicon glyphicon-king"></i> 图片管理</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 用户标签</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 层级关系</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 活动分析</a> 
							</li>
						</ul>
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          	<h1 class="page-header">图片管理</h1>
          	<h3 class="page-header">
          	选择主题：
	          	<select>
					<option>全部</option>
					<option>餐饮</option>
					<option>教育</option>
					<option>电商</option>
					<option>金融</option>
					<option>旅游</option>
					<option>零售</option>
				</select>
          	</h3>
          <div class="row placeholders">
              <div class="col-xs-6 col-sm-3 placeholder">
	              <img src="../PicturePage/activity_img/education_img/programming1.jpg" class="img-responsive">
	              <h4>Label</h4>
	              <span class="text-muted">Something else</span>
              </div>
	          <div class="col-xs-6 col-sm-3 placeholder" style="border-radius: 0;">
	              <img src="../PicturePage/activity_img/education_img/programming2.jpg" class="img-responsive">
	              <h4>Label</h4>
	              <span class="text-muted">Something else</span>
	          </div>
	          <div class="col-xs-6 col-sm-3 placeholder">
	              <img src="../PicturePage/activity_img/education_img/programming3.jpg" class="img-responsive">
	              <h4>Label</h4>
	              <span class="text-muted">Something else</span>
	          </div>
	          <div class="col-xs-6 col-sm-3 placeholder">
				  <img src="..\PicturePage\activity_img\Tourism_img\Tourism1.jpg" class="img-responsive">              
				  <h4>Label</h4>
	              <span class="text-muted">Something else</span>
	          </div>
          </div>
          <div class="paging">
          	  <input type="button" value="<< 上一页"/>
          	  <ul class="paging_ul">
          	  	<li>...</li>
          	  	<li>1</li>
          	  	<li>2</li>
          	  	<li>3</li>
          	  	<li>...</li>
          	  </ul>
          	  <input type="text" class="text" />
          	  <input type="button" value="跳转"/>
          	  <input type="button" value="下一页  >>"/>
          </div>
          <div id="searchResult">
          
          </div>
        </div>
      </div>
    </div>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="script/docs.min.js"></script>
    <script type="text/javascript">
        var 
        $(function () {
			$(".list-group-item").click(function(){
            	// jquery对象的回调方法中的this关键字为DOM对象
                // $(DOM) ==> JQuery
				if ( $(this).find("ul") ) { // 3 li
					$(this).toggleClass("tree-closed");
					if ( $(this).hasClass("tree-closed") ) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
        });
        var src = document.getElementsByClassName("img-responsive").value;
        //创建和后端数据交互的XML对象
        var request = new XMLHttpRequest();
        request.open("GET","/ImgServlet.java?SRC="+src,true);
        //
        request.send();
        //
        var data
        //响应后台数据的回调函数
        request.onreadystatechange=function(){
        	if (request.readyState === 4) {
                if (request.status === 200) {
                    var data = JSON.parse(request.responseText);
                    if(data.success){
                        document.getElementById("searchResult").innerHTML = data.msg;
                    }else{
                        document.getElementById("searchResult").innerHTML = "出现错误:"+data.msg;
                    }
                } else {
                    alert("发生错误:" + request.status);
                }
            }
        }
	    
	</script>
  </body>
</html>