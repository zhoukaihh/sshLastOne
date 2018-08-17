<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	 创建用户
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="CreateUserForm" >
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">登录名</label>
                       						 <div class="col-sm-10">
                        					<input class="form-control" placeholder="请输入登录名" name="loginName">
                                        	</div>
                                        </div>	
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">密码</label>
                       							 <div class="col-sm-10">
                        							<input class="form-control" placeholder="请输入密码" name="password" type="password">
                       							 </div>
                                        </div>
                                       	<div class="form-group">
                        					<label class="col-sm-2 control-label">姓名</label>
                       						 <div class="col-sm-10">
                       							 <input class="form-control" placeholder="请输入姓名" name="name">
                       						 </div>
                    					</div>
                    					<div class="form-group">
                        					<label class="col-sm-2 control-label">性别</label>
                       						 <div class="col-sm-10">
                       							 <input class="form-control" placeholder="请输入性别" name="gender">
                       						 </div>
                    					</div>
                                        <div class="form-group">
                        					<label class="col-sm-2 control-label">创建时间</label>
                       						 <div class="col-sm-10">
                       							 <input type="datetime-local" class="form-control" placeholder="请输入创建时间" name="createTime">
                       						 </div>
                    					</div>
                    					<div class="form-group input-group">
                                        	<input type="hidden" name="roleIds" id="userFormRoleIds">
                                            <input type="text" class="form-control" placeholder="请选择角色" id="userFormRoleNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectUserRoles();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" class="btn btn-default" onclick="submitUserCreateForm();">提交</button>
                                        <button type="reset" class="btn btn-default">重置</button>
                                    </form>
                                </div>
                               
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
                <script type="text/javascript">
                	function submitUserCreateForm () {
                		
                		$.post ('${pageContext.request.contextPath}/user/create',
                				$('#CreateUserForm').serializeArray(), function (result) {
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
			