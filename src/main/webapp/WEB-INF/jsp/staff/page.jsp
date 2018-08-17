<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			<div class="row">
                <div class="col-lg-12">
                    <h4>员工管理</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="staffTable">
                                <thead>
                                    <tr>
                                        <th>员工姓名</th>
                                        <th>性别</th>
                                        <th>出生日期</th>
                                        <th>手机号码</th>
                                        <th>职位</th>
                                        <th>用户名</th>
                                        <th width="150">操作</th>
                                    </tr>
                                </thead>
                            </table>
                            <!-- /.table-responsive -->
                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <script>
            $(document).ready(function() {
            	var tableId = 'staffTable';
            	// 初始化列表，并触发后台的json数据获取
                var table = $('#' + tableId).DataTable({
                	"processing": true,
                    "serverSide": true,
                    "rowId" : 'id',
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/staff/list",
                        "type": "GET"
                    },
                    "order": [[ 0, "asc" ]] ,
                    "columns": [
                    	{ "data": "name" },
                    	{ "data": "gender", "orderable" : false},
                        { "data": "birthday" , "orderable" : false},
                        { "data": "mobile", "orderable" : false},
                        { "data": "positionNames", "orderable" : false},
                        { "data": "userName", "orderable" : false},
                        // 下面在操作列中添加了修改和删除按钮
                       { "data": null , "orderable" : false, "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a> <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"}
                    ]
                });
                // 设置列表中按钮的事件
                $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                	// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象，及UserDto
                    var data = table.row( $(this).parents('tr') ).data();
                	
                 // 调用创建方法
                	if ($(this).attr('name') == 'create') {
                		createStaff();
                	}
                	// 调用修改方法
                	if ($(this).attr('name') == 'update') {
                		updateStaff(data.id);
                	}
                	// 调用删除方法
					if ($(this).attr('name') == 'delete') {
						deleteStaff (data.id);
                	}
                } );
             	// 设置列表多选
                $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	// 如果有info样式则删除，没有则添加
                	$(this).toggleClass('info');
                } );
             	
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createStaff();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteStaffs();'>批量删除</a>");
            });
            
            function createStaff(){
            	$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/create');
            }
            
            function updateStaff(){
            	$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/update?id=' + id);
            }
            
            function deleteStaff (id) {
            	if (!confirm ('确定要删除选定的员工吗？')) {
            		return;
            	}
            	$.post ('${pageContext.request.contextPath}/staff/delete', {id : id}, function (result) {
		    		if (result.success) {
		    			var tableId = 'staffTable';
				    	var table = $('#' + tableId).DataTable();
		    			table.rows('.info').remove().draw(false);
		    		} 
		    		alert (result.message);
		    	});
            }
            
            function updateStaff (id) {
            	$('#page-wrapper').load ('${pageContext.request.contextPath}/staff/update?id=' + id);
            }
            
            
            function deleteStaffs () {
            	// 获取DataTable
		    	var tableId = 'staffTable';
		    	var table = $('#' + tableId).DataTable();
		    	// 查找样式为info的行，包含所有staffDto属性
		    	var rows = table.rows('.info').data();
		    	// 判断是否有选择
		    	if (rows.length == 0) {
		    		alert ('请选择至少一个人员！');
		    		return;
		    	}
		    	// 确认删除
		    	if (!confirm ('是否删除选中的人员？')) {
		    		return;
		    	}
		    	// 拼接id字符串，多个id逗号隔开
		    	var ids = '';
		    	for (var i = 0; i < rows.length; i ++) {
		    		if (ids != '') {
		    			ids += ',';
		    		}
		    		ids += rows[i].id;
		    	}
		    	// 发送ajax请求，如果成功刷新本页面，否则提示用户操作失败
		    	deleteStaff(ids);
		    }
		    </script>