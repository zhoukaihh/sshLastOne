<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        	   修改用户	
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="${pageContext.request.contextPath}/user/update" method="post">
                                        <div class="form-group">
                                            <label>登录名</label>
                                            <input class="form-control"  name="loginName" value="${user.loginName}" >
                                             <input type="hidden" class="form-control"  name="id" value="${user.id}" >
                                        </div>
                                        <div class="form-group">
                                            <label>姓名</label>
                                            <input class="form-control"  name="name" value="${user.name}" >
                                        </div>
                                        <div class="form-group">
                                            <label>密码</label>
                                            <input class="form-control"  name="password" value="${user.password}" >
                                        </div>
                                        <div class="form-group">
                                            <label>性别</label>
                                            <input class="form-control"  name="gender" value="${user.gender}" >
                                        </div>
                                        <div class="form-group">
                                            <label>创建时间</label>
                                            <input type="datetime-local" class="form-control"  name="createTime" value="${user.createTime}" >
                                        </div>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="roleIds" id="userFormRoleIds" value="${user.roleIds}">
                                            <input type="text" class="form-control" placeholder="请选择角色" id="userFormRoleNames" name="roleNames" value="${user.roleNames}">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectUserRoles();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="submit" class="btn btn-default">提交</button>
                                        <button type="reset" class="btn btn-default">重置</button>
                                        
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
                <script type="text/javascript">
                	function submitUserUpdateForm () {
                		$.post ('${pageContext.request.contextPath}/user/update', $('#userUpdateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/user');
                			} else {
                				alert (result.message);
                			}
                		});
                	}
                	function selectUserRoles () {
                		$('#page-wrapper').hide().attr('id', "page-wrapper2");
                		$('#wrapper').append ('<div id="page-wrapper">角色列表选择</div>');
                		$('#page-wrapper').load ('${pageContext.request.contextPath}/user/roles');
                	}
                	
                </script>
                
                
            </div>
            <!-- /.row -->
     