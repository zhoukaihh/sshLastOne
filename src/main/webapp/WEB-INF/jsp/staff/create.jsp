<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	 创建人员
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="CreateStaffForm" enctype="multipart/form-data" method="post">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">姓名</label>
                       						 <div class="col-sm-10">
                        					<input class="form-control" placeholder="请输入姓名" name="name">
                                        	</div>
                                        </div>	
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">性别</label>
                       							 <div class="col-sm-10">
                        							<input class="form-control" placeholder="请输入性别" name="gender" >
                       							 </div>
                                        </div>
                                       	<div class="form-group">
                        					<label class="col-sm-2 control-label">出生日期</label>
                       						 <div class="col-sm-10">
                       							 <input type="datetime-local" class="form-control" placeholder="请输入出生日期" name="birthday">
                       						 </div>
                    					</div>
                    					<div class="form-group">
                        					<label class="col-sm-2 control-label">电话</label>
                       						 <div class="col-sm-10">
                       							 <input class="form-control" placeholder="请输入电话" name="mobile">
                       						 </div>
                    					</div>
                                        <div class="form-group">
                        					<label class="col-sm-2 control-label">头像</label>
                       						 <div class="col-sm-10">
                       							 <input  class="form-control" placeholder="请输入头像" name="headImage1" type="file">
                       						 </div>
                    					</div>
                    					<div class="form-group input-group">
                                        	<input type="hidden" name="positionIds" id="staffFormPositionIds">
                                            <input type="text" class="form-control" placeholder="请选择职位" id="staffFormPositionNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectStaffPositions();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" class="btn btn-default" onclick="submitStaffCreateForm();">提交</button>
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
                function submitStaffCreateForm () {
        			$.ajax({
        				url :'${pageContext.request.contextPath}/staff/create',
        				method : 'POST',
        				data : new FormData(document.getElementById('CreateStaffForm')),
        				contentType : false, // 注意这里应设为false
        				processData : false,
        				cache : false,
        				success : function(json) {
        					alert (json.message);
        					$('#page-wrapper').load ('${pageContext.request.contextPath}/staff');
        				},
        				error : function(jqXHR) {
        					alert ('失败！');
        				}
        			});
        		}
                	
                	function selectStaffPositions () {
                		$('#page-wrapper').hide().attr('id', "page-wrapper2");
                		$('#wrapper').append ('<div id="page-wrapper">职位列表选择</div>');
                		$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/positions');
                	}
                </script>
            </div>
            <!-- /.row -->
			