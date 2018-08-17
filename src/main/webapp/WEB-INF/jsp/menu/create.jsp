<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	 创建父菜单
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="CreateMenuForm" >
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">菜单名</label>
                       						 <div class="col-sm-10">
                        					<input class="form-control" placeholder="请输入菜单名" name="name">
                                        	</div>
                                        </div>	
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">图标</label>
                       							 <div class="col-sm-10">
                        							<input class="form-control" placeholder="请输入图标" name="icon" ">
                       							 </div>
                                        </div>
                                       	<div class="form-group">
                        					<label class="col-sm-2 control-label">URL</label>
                       						 <div class="col-sm-10">
                       							 <input class="form-control" placeholder="请输入URL" name="url">
                       						 </div>
                    					</div>
                    					<div class="form-group">
                        					<label class="col-sm-2 control-label">编码</label>
                       						 <div class="col-sm-10">
                       							 <input class="form-control" placeholder="请输入编码" name="no">
                       						 </div>
                    					</div>
                                        <button type="button" class="btn btn-default" onclick="submitMenuCreateForm();">提交</button>
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
                	function submitMenuCreateForm () {
                		
                		$.post ('${pageContext.request.contextPath}/menu/create',
                				$('#CreateMenuForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/menu');
                			} else {
                				alert (result.message);
                			}
                		});
                	} 
                </script>
            </div>
            <!-- /.row -->
			