<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	修改人员
                           	<div style="float: right;margin-top: 32px" >
                           		<img src="${pageContext.request.contextPath}/files/timg.jpg" width="70px" height="70px">
                           	</div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" id="staffUpdateForm">
                                    	
                                        <div class="form-group">
                                            <label>姓名</label>
                                            <input class="form-control"  name="name" value="${staff.name}" >
                                        	<input type="hidden" class="form-control"  name="id" value="${staff.id}" >
                                        	<input type="hidden" class="form-control"  name="oldMobile" value="${staff.mobile}" >
                                        </div>
                                       	<div class="form-group">
                                            <label>性别</label>
                                            <input class="form-control"  name="gender" value="${staff.gender}">
                                        </div>
                                        <div class="form-group">
                                            <label>出生日期</label>
                                            <input type="datetime-local" class="form-control"  name="birthday" value="${staff.birthday}">
                                        </div>
                                        <div class="form-group">
                                            <label>电话</label>
                                            <input class="form-control"  name="mobile" value="${staff.mobile}">
                                        </div>
                                        <div class="form-group">
                                            <label>头像</label>
                                            <input class="form-control"  name="headImage" value="${staff.headImage}">
                                        </div>
                                        <div class="form-group input-group">
                                        	<input type="hidden" name="positionIds" id="staffFormPositionIds" value="${staff.positionIds}">
                                            <input type="text" class="form-control" value="${staff.positionNames}" id="staffFormPositionNames">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" onclick="selectStaffPositions();"><i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                        <button type="button" onclick="submitStaffUpdateForm ();" class="btn btn-default">提交</button>
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
                	function submitStaffUpdateForm () {
                		$.post ('${pageContext.request.contextPath}/staff/update', $('#staffUpdateForm').serializeArray(), function (result) {
                			if (result.success) {
                				$('#page-wrapper').load ('${pageContext.request.contextPath}/staff');
                			} else {
                				alert (result.message);
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
