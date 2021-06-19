let tableIns;
let tableInsOnLine;
let tree;
layui.use(['element', 'form', 'table', 'layer', 'laydate', 'tree', 'util'], function () {
    let table = layui.table;
    let form = layui.form;//select、单选、复选等依赖form
    let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    let laydate = layui.laydate;
    tree = layui.tree;
    let height = document.documentElement.clientHeight - 160;

    //用户列表
    tableIns = table.render({
        elem: '#userTable'
        , url: ctx + '/tender/page'
        , method: 'POST'
        //请求前参数处理
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'flag' //规定数据状态的字段名称，默认：code
            , statusCode: true //规定成功的状态码，默认：0
            , msgName: 'msg' //规定状态信息的字段名称，默认：msg
            , countName: 'records' //规定数据总数的字段名称，默认：count
            , dataName: 'rows' //规定数据列表的字段名称，默认：data
        }
        //响应后数据处理
        , parseData: function (res) { //res 即为原始返回的数据
            var data = res.data;
            return {
                "flag": res.flag, //解析接口状态
                "msg": res.msg, //解析提示文本
                "records": data.records, //解析数据长度
                "rows": data.rows //解析数据列表
            };
        }
        , toolbar: '#userTableToolbarDemo'
        , title: '用户列表'
        , cols: [[
              {field: 'id', title: 'ID', hide: true}
            , {field: 'userName', title: '姓名'}
            , {field: 'phone', title: '手机号'}
            , {field: 'idCard', title: '身份证号'}
            , {field: 'tenderNo', title: '投标单号'}
            , {field: 'createTime', title: '登记时间'}
        ]]
        , defaultToolbar: ['', '', '']
        , page: true
        , cellMinWidth: 80,
        done:function(res){
        }
    });


    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        switch (obj.event) {
            case 'query':
                let userName = $("#userName").val();
                let phone = $("#phone").val();
                let idCard = $("#idCard").val();
                let tenderNo = $("#tenderNo").val();
                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        // this.where = {};
                    }
                };
                //设定异步数据接口的额外参数
                query.where = {
                    userName: userName,
                    phone:phone,
                    idCard:idCard,
                    tenderNo:tenderNo
                };
                tableIns.reload(query);
                $("#userName").val(userName);
                $("#phone").val(phone);
                $("#idCard").val(idCard);
                $("#tenderNo").val(tenderNo);
                break;
            case 'reload':
                tableInsOnLine.reload();
                break;
            case 'clear':
                $("#userName").val('');
                $("#phone").val('');
                $("#idCard").val('');
                $("#tenderNo").val('');
                break;
            case 'export':
                window.location.href = ctx + "/tender/download";
                break;
        }
    });

    //日期选择器
    laydate.render({
        elem: '#expiredTimeDate',
        format: "yyyy-MM-dd HH:mm:ss"
    });
});


/**
 * 加载用户权限
 */
function loadAuthorityTree() {
    let userForm = $("#userForm").serializeObject();
    //获取菜单数据
    $.post(ctx + "/sys/sysUserAuthority/findUserAuthorityAndAllSysAuthorityByUserId", userForm, function (data) {
        //数据说明：id对应id，title对应menuName，href对应menuPath
        let treeData = [];
        let userTreeString = JSON.stringify(data.data.sysUserAuthorityVoList);
        for (let authority of data.data.sysAuthorityVoList) {
            let tree = {
                title: authority.authorityName
                , id: authority.authorityId
                , spread: true
            };
            //回显用户权限
            if(userTreeString.search(authority.authorityId) !== -1){
                tree.checked = true;
            }
            treeData.push(tree);
        }

        //开启节点操作图标
        tree.render({
            elem: '#userAuthorityTree'
            , id: 'userAuthorityTree'
            , data: [{
                title: '系统权限根节点'
                , href: "/"
                , id: 0
                , spread: true
                , children: treeData
            }]
            , showCheckbox: true
        });
    });
}