<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=gb2312">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <title>OICPRAS</title>
    <link rel="stylesheet" href="./static/plug/layui/css/layui.css">
    <%--网页图标--%>
    <link rel="shortcut icon" href="static/images/COIPIB.png" type="image/x-icon">
    <!--font-awesome-->
    <link href="./static/plug/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!--全局样式表-->
    <link href="./static/css/global.css" rel="stylesheet"/>
    <%--分页样式表--%>
    <link href="./static/css/pageInfo/page.css" rel="stylesheet">
    <%--index页样式--%>
    <link href="./static/css/index.css" rel="stylesheet">
    <%--下拉列表样式--%>
    <link href="./static/css/fSelect.css" rel="stylesheet">

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header position: absolute;">
        <a href="${ctx}/index">
            <div class="layui-logo doc-logo" style="font-weight: bold">OICPRAS</div>
        </a>
        <ul class="layui-nav layui-layout-left small-head-nav-left">
            <li class="layui-nav-item"><a href="javascript:;"></a></li>
        </ul>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left head-nav-left">
            <li class="layui-nav-item">
                <form class="layui-form" action="" style="color:black !important">
                    <div class="layui-form-item" style="margin: auto 0;">
                        <div class="layui-input-inline">
                            <select id="compare" lay-filter="compare">
                                <option selected="selected" value="-1">选择比较方式</option>
                                <option value="0">时间序列</option>
                                <option value="1">空间序列</option>
                            </select>
                        </div>
                        <div class="layui-input-inline" style="margin: auto 0;">
                            <select id="format" lay-filter="format">
                                <option selected="selected" value="-1">选择显示格式</option>
                            </select>
                        </div>
                    </div>
                </form>
            </li>
            <li class="layui-nav-item"><a href="javascript:;" onclick="doClickHorizontalMenu('false', 'true');">管理员</a>
            </li>
            <li class="layui-nav-item"><a href="${ctx}/" onclick="alert(help);">帮助</a></li>index
            <li class="layui-nav-item"><a href="${ctx}/addCountry">上传</a></li>
            <!--禁止删除-->
        </ul>



        <ul class="layui-nav layui-layout-right head-nav-right">
            <li class="layui-nav-item doc-login" id="loginButton" style="display: none;"><a href="${ctx}/login">登录</a>
            </li>
            <li class="layui-nav-item" id="userInfoButton" style="display: none; margin-right: 20px;"></li>
        </ul>

        <a class="small-doc-navicon" href="javascript:;" onclick="showLeftNav();">
            <i class="fa fa-navicon"></i>
        </a>
    </div>


    <div class="layui-side layui-bg-black left-nav-index" style="width:20% !important;">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <%--<ul class="layui-nav layui-nav-tree" lay-filter="test" id="verticalMenu">--%>

            <%--</ul>--%>
            <form id="time-country">
                <br/>
                请选择时间：
                <div class="container" style="color:black !important;background-color:white !important;">
                    <select id="time-select" class="multi-dropdown" multiple="multiple">
                        <option value="">选择时间</option>
                        <%
                            for (int i = 2017; i >= 2015; i--) {
                        %>
                                <option value="<%=i%>"><%=i%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <br/>
                请选择国家：
                <div class="container" style="color:black !important;background-color:white !important;">
                    <select id="country-select" class="multi-dropdown" multiple="multiple">
                        <option value="">选择国家或地区</option>
                        <option value='AFG'>阿富汗</option>
                        <option value='AGO'>安哥拉</option>
                        <option value='ALB'>阿尔巴尼亚</option>
                        <option value='ARE'>阿联酋</option>
                        <option value='ARG'>阿根廷</option>
                        <option value='ARM'>亚美尼亚</option>
                        <option value='AUS'>澳大利亚</option>
                        <option value='AUT'>奥地利</option>
                        <option value='AZE'>阿塞拜疆</option>
                        <option value='BDI'>布隆迪</option>
                        <option value='BEL'>比利时</option>
                        <option value='BEN'>贝宁</option>
                        <option value='BFA'>布基纳法索</option>
                        <option value='BGD'>孟加拉</option>
                        <option value='BGR'>保加利亚</option>
                        <option value='BHR'>巴林</option>
                        <option value='BIH'>波黑</option>
                        <option value='BLR'>白俄罗斯</option>
                        <option value='BLZ'>伯利兹</option>
                        <option value='BOL'>玻利维亚</option>
                        <option value='BRA'>巴西</option>
                        <option value='BRB'>巴巴多斯</option>
                        <option value='BRN'>文莱</option>
                        <option value='BTN'>不丹</option>
                        <option value='SSD'>南苏丹</option>
                        <option value='BWA'>博茨瓦纳</option>
                        <option value='CAF'>中非</option>
                        <option value='CAN'>加拿大</option>
                        <option value='CHE'>瑞士</option>
                        <option value='CHL'>智利</option>
                        <option value='CIV'>科特迪瓦</option>
                        <option value='CMR'>喀麦隆</option>
                        <option value='COG'>刚果布</option>
                        <option value='COL'>哥伦比亚</option>
                        <option value='COM'>科摩罗</option>
                        <option value='CRI'>哥斯达黎加</option>
                        <option value='CYP'>塞浦路斯</option>
                        <option value='CZE'>捷克</option>
                        <option value='DEU'>德国</option>
                        <option value='DJI'>吉布提</option>
                        <option value='DMA'>多米尼克</option>
                        <option value='DNK'>丹麦</option>
                        <option value='DOM'>多米尼加</option>
                        <option value='DZA'>阿尔及利亚</option>
                        <option value='ECU'>厄瓜多尔</option>
                        <option value='EGY'>埃及</option>
                        <option value='ESP'>西班牙</option>
                        <option value='EST'>爱沙尼亚</option>
                        <option value='ETH'>埃塞俄比亚</option>
                        <option value='FIN'>芬兰</option>
                        <option value='FJI'>斐济</option>
                        <option value='FRA'>法国</option>
                        <option value='FSM'>密克罗尼西亚</option>
                        <option value='GAB'>加蓬</option>
                        <option value='GBR'>英国</option>
                        <option value='GEO'>格鲁吉亚</option>
                        <option value='GHA'>加纳</option>
                        <option value='GIN'>几内亚</option>
                        <option value='GMB'>冈比亚</option>
                        <option value='GNB'>几内亚（比绍）</option>
                        <option value='GNQ'>赤道几内亚</option>
                        <option value='GRC'>希腊</option>
                        <option value='GRD'>格林纳达</option>
                        <option value='GTM'>危地马拉</option>
                        <option value='GUY'>圭亚那</option>
                        <option value='HND'>洪都拉斯</option>
                        <option value='HRV'>克罗地亚</option>
                        <option value='HTI'>海地</option>
                        <option value='HUN'>匈牙利</option>
                        <option value='IDN'>印度尼西亚</option>
                        <option value='IND'>印度</option>
                        <option value='IRL'>爱尔兰</option>
                        <option value='IRN'>伊朗</option>
                        <option value='IRQ'>伊拉克</option>
                        <option value='ISL'>冰岛</option>
                        <option value='ISR'>以色列</option>
                        <option value='ITA'>意大利</option>
                        <option value='JAM'>牙买加</option>
                        <option value='JOR'>约旦</option>
                        <option value='JPN'>日本</option>
                        <option value='KAZ'>哈萨克斯坦</option>
                        <option value='KEN'>肯尼亚</option>
                        <option value='KGZ'>吉尔吉斯斯坦</option>
                        <option value='KHM'>柬埔寨</option>
                        <option value='KOR'>韩国</option>
                        <option value='KWT'>科威特</option>
                        <option value='LAO'>老挝</option>
                        <option value='LBN'>黎巴嫩</option>
                        <option value='LBR'>利比里亚</option>
                        <option value='LBY'>利比亚</option>
                        <option value='LKA'>斯里兰卡</option>
                        <option value='LSO'>莱索托</option>
                        <option value='LTU'>立陶宛</option>
                        <option value='LUX'>卢森堡</option>
                        <option value='LVA'>拉脱维亚</option>
                        <option value='MAR'>摩洛哥</option>
                        <option value='MDA'>摩尔多瓦</option>
                        <option value='MDG'>马达加斯加</option>
                        <option value='MDV'>马尔代夫</option>
                        <option value='MEX'>墨西哥</option>
                        <option value='MKD'>马其顿</option>
                        <option value='MLI'>马里</option>
                        <option value='MLT'>马耳他</option>
                        <option value='MMR'>缅甸</option>
                        <option value='MNG'>蒙古</option>
                        <option value='MNE'>黑山</option>
                        <option value='MOZ'>莫桑比克</option>
                        <option value='MRT'>毛里塔尼亚</option>
                        <option value='MUS'>毛里求斯</option>
                        <option value='MWI'>马拉维</option>
                        <option value='MYS'>马来西亚</option>
                        <option value='NAM'>纳米比亚</option>
                        <option value='NER'>尼日尔</option>
                        <option value='NGA'>尼日利亚</option>
                        <option value='NIC'>尼加拉瓜</option>
                        <option value='NLD'>荷兰</option>
                        <option value='NOR'>挪威</option>
                        <option value='NPL'>尼泊尔</option>
                        <option value='NZL'>新西兰</option>
                        <option value='OMN'>阿曼</option>
                        <option value='PAK'>巴基斯坦</option>
                        <option value='PAN'>巴拿马</option>
                        <option value='PLW'>帕劳</option>
                        <option value='PER'>秘鲁</option>
                        <option value='PHL'>菲律宾</option>
                        <option value='PNG'>巴布亚新几内亚</option>
                        <option value='POL'>波兰</option>
                        <option value='PRT'>葡萄牙</option>
                        <option value='PRY'>巴拉圭</option>
                        <option value='QAT'>卡塔尔</option>
                        <option value='ROM'>罗马尼亚</option>
                        <option value='RUS'>俄罗斯</option>
                        <option value='RWA'>卢旺达</option>
                        <option value='WSM'>萨摩亚</option>
                        <option value='SAU'>沙特</option>
                        <option value='SDN'>苏丹</option>
                        <option value='SEN'>塞内加尔</option>
                        <option value='SGP'>新加坡</option>
                        <option value='SLE'>塞拉利昂</option>
                        <option value='SLV'>萨尔瓦多</option>
                        <option value='SUR'>苏里南</option>
                        <option value='SVK'>斯洛伐克</option>
                        <option value='SVN'>斯洛文尼亚</option>
                        <option value='SWE'>瑞典</option>
                        <option value='SYC'>塞舌尔</option>
                        <option value='SYR'>叙利亚</option>
                        <option value='TCD'>乍得</option>
                        <option value='TGO'>多哥</option>
                        <option value='THA'>泰国</option>
                        <option value='TJK'>塔吉克斯坦</option>
                        <option value='TKM'>土库曼斯坦</option>
                        <option value='TMP'>东帝汶</option>
                        <option value='TON'>汤加</option>
                        <option value='TTO'>特立尼达和多巴哥</option>
                        <option value='TUN'>突尼斯</option>
                        <option value='TUR'>土耳其</option>
                        <option value='TZA'>坦桑尼亚</option>
                        <option value='UGA'>乌干达</option>
                        <option value='UKR'>乌克兰</option>
                        <option value='URY'>乌拉圭</option>
                        <option value='USA'>美国</option>
                        <option value='UZB'>乌兹别克斯坦</option>
                        <option value='VCT'>圣文森特和格林纳丁斯</option>
                        <option value='VEN'>委内瑞拉</option>
                        <option value='VNM'>越南</option>
                        <option value='VUT'>瓦努阿图</option>
                        <option value='WBG'>巴勒斯坦</option>
                        <option value='YEM'>也门</option>
                        <option value='SRB'>塞尔维亚</option>
                        <option value='ZAF'>南非</option>
                        <option value='ZAR'>刚果金</option>
                        <option value='ZMB'>赞比亚</option>
                        <option value='ZWE'>津巴布韦</option>
                    </select>
                </div>
                <br/>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="layui-body left-nav-body">
        <div class="blog-main" style="margin: 150px 0 0 0">
            <!--左边栏目-->
            <div id="information" style="float:right;height:370px;width:300px;"></div>
            <div id="chartContainer" style="height: 370px; width: 50%;margin: auto auto;"></div>
            <div><span class="share">立即分享</span></div>
        </div>
        <div class="clear"></div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        <button class="layui-btn layui-btn-primary layui-btn-sm" id="back" style="display: none"
                onclick="clickBackBtn();">
            <i class="fa fa-chevron-left" aria-hidden="true"></i>
        </button>
    </div>
</div>
<input type="hidden" id="isEdit" value="false">
<input type="hidden" id="isActive" value="false">
<!--二维码弹层-->
<div id="popQRCode"><div id="qrcode" style="margin-left: 30px; margin-top: 8px;"></div></div>
</body>

<script src="./static/plug/layui/layui.js"></script>
<script src='./static/js/jquery/jquery.min.js'></script>
<script src='./static/js/pdfobject.js'></script>
<script src="./static/plug/qrcodejs/qrcode.js"></script>
<script src="./static/js/canvasjs.min.js"></script>
<script src="./static/js/fSelect.js"></script>
<script>

    // js全局变量
    var userInfo = {};
    userInfo.id = "${user.id}";
    userInfo.name = "${user.name}";
    userInfo.email = "${user.email}";
    userInfo.level = "${user.level}";
    userInfo.active = "${user.active}";
    // alert(userInfo.id);

    var cmpSelect = $('#compare');
    var formatSect = $('#format');
    //0是时间序列 1是空间序列
    var compare = -1;
    //1是柱状图 2是折线图 3是条形图 4是雷达图
    var format = -1;
    var descripMap = {
        'S':'风险发生将导致中国境外产业园区建设目标失败',
        'H':'风险发生将导致中国境外产业园区建设目标严重受损，造成重大影响',
        'M':'风险发生对中国境外产业园区建设目标造成中度影响，但仍能部分实现',
        'L':'风险发生对中国境外产业园区建设部分目标实现产生影响，但不影响整体目标',
        'N':'风险发生对中国境外产业园区建设目标造成的影响可以忽略，且不影响整体目标',
    };
    var help = "软件名称：Web境外国际合作园区风险评估软件1.0\n" +
        "软件版本号：V1.0\n" +
        "开发机构：东南大学\n" +
        "开发人员：赵四东、王兴平、王慧、张蒙\n" +
        "编程人员：戚晓芳、刘恩赐、徐成龙、贺黎、喻学乐\n";

    var authEnum = ["游客可见", "注册用户可见", "VIP用户可见", "管理员可见"];
    var activeEnum = ["通过", "待审核", "未通过"];

    // layui框架导航模块初始化，禁止删除
    var layer, element, form;
    layui.use(['element', 'layer', 'form'], function () {
        element = layui.element;
        layer = layui.layer;
        form = layui.form;

        form.on('select(compare)',function(data){
            compare = cmpSelect.val();
            //时间序列方式
            if(compare == 0) {
                formatSect.empty();
                formatSect.append("<option value='-1'>选择显示格式</option>");
                formatSect.append("<option value='1'>柱状图</option>");
                formatSect.append("<option value='2'>折线图</option>");

            } else if (compare == 1) {
                formatSect.empty();
                formatSect.append("<option value='-1'>选择显示格式</option>");
                formatSect.append("<option value='1'>柱状图</option>");
                formatSect.append("<option value='2'>折线图</option>");
                formatSect.append("<option value='3'>条形图</option>");
                formatSect.append("<option value='4'>雷达图</option>");

            }

            form.render();
        });

        form.on('select(format)',function(data){
            format = formatSect.val();
        });
    });

    $(function () {;
        checkUserLogin();
    });

    $(function () {
       $('#time-select').on('change',function () {
            if (compare == 0) {

            } else if (compare == 1) {
                if ($('#time-select option:selected').length > 1) {
                    alert('当前为空间序列模式，时间必须单选');
                }
            } else if (compare == "-1") {
                // alert("请先选择比较方式!");
            }
       });

       $('#country-select').on('change',function () {
           if (compare == 0) {
               if ($('#country-select option:selected').length > 1) {
                   alert('当前为时间序列模式，国家必须单选');
               }
           } else if (compare == 1) {

           } else if (compare == "-1") {
               // alert("请先选择比较方式!");
           }

       });
    });


    //选择时间和选择国家两个多选下拉菜单, 并采用ajax异步请求
    $(function() {
        $('.multi-dropdown').fSelect();
        $('#time-country').submit(function () {
            if (compare == "-1") {
                alert('请选择比较方式！');
                return false;
            }

            if (format == "-1") {
                alert('请选择显示格式!');
                return false;
            }

            var page = '${ctx}/country/search';
            var time = [];
            var country = [];
            times = document.getElementById('time-select');
            for (var i = 0; i < times.length; i++) {
                if (times.options[i].selected) {
                    time.push(times.options[i].value);
                }
            }

            countries = document.getElementById('country-select');
            for (var i = 0; i < countries.length; i++) {
                if (countries.options[i].selected) {
                    country.push(countries.options[i].value);
                }
            }

            //检查输入数据的合法性
            if (compare == 0) {
                if (country.length > 1) {
                    alert('选择国家数目超过限制!');
                    return false;
                }
            } else if (compare == 1){
                if (time.length > 1) {
                    alert('选择年份超过限制!');
                    return false;
                }
            }

            var data = {'type':compare,
                         'time': JSON.stringify(time),
                         'countries':JSON.stringify(country)
                        };
            $.ajax({
                url: page,
                type: 'post',
                //contextType:'application/json',
                data: data,
                success: function (result) {
                    $('#information').html("");
                    console.log(result);
                    jsonData = eval('(' + result + ')');
                    // data0 = {'type': 0,
                    //     'time': [2017,2018,2019],
                    //     'country': "阿富汗",
                    //     'data':[[1.1,1.3,1.5,1.2],[1.7,2.3,1.1,2.3],[1.2,1.0,1.8,1.7]]};
                    // data1 = {'type': 1,
                    //         'time': 2019,
                    //         'country': ['USA','UK','China'],
                    //         'data':[[2.1,2.3,2.1,2.6],[2.1,2.3,2.1,2.6],[2.1,2.3,2.1,2.6]]};
                    //         'level':'l'
                    //         'auth':0:游客 1:注册用户
                    var type = jsonData['type'];
                    var auth = jsonData['auth'];
                    console.log( auth == "0");
                    console.log( auth == "1");
                    if (type == 0) {
                        var yearLen = jsonData['time'].length;
                        if (auth == "0") {
                            if (yearLen > 2) {
                                alert("您为游客，最多可选择的年份数量为2，请重新选择！");
                                return;
                            }
                        } else if (auth == "1") {
                            if (yearLen > 5) {
                                alert("您为注册用户，最多可选择的年份数量为2，请重新选择！");
                                return;
                            }
                        }
                    } else if (type == 1) {
                        var countryLen = jsonData['country'].length;
                        if (auth == "0") {
                            if (countryLen > 3) {
                                alert("您为游客，最多可选择的国家数量为3，请重新选择！");
                                return;
                            }
                        } else if (auth == "1") {
                            if (countryLen > 10) {
                                alert("您为注册用户，最多可选择的国家数量为10，请重新选择！");
                                return;
                            }
                        }

                    }

                    if (type == 0) {
                        if (format == 1) {
                            showBarChart(jsonData);
                        } else if (format == 2) {
                            showLineChart(jsonData);
                        }
                    } else {
                        showBarChart(jsonData);
                    }

                    var flag = false;
                    if (jsonData['type'] == 0 && jsonData['time'].length == 1) flag = true;
                    if (jsonData['type'] == 1 && jsonData['country'].length == 1) flag = true;
                    if (flag) {
                        var innerHTML = "                <div style=\"height:40px;width:200px;text-align: center;\">综合风险指数</div>\n" +
                            "                <div style=\"height:30px;margin-bottom: 20px;width:200px;background-color:lightskyblue;text-align: center;\">"+jsonData['data'][0][3]+"(数值)</div>\n" +
                            "                <div style=\"height:30px;margin-bottom: 20px;width:200px;background-color:lightskyblue;text-align: center\">"+jsonData['level'][0]+"(等级)</div>\n" +
                            "                <div style=\"width:200px;background-color:lightskyblue;text-align: center\">"+descripMap[jsonData['level'][0]]+"</div>";
                        console.log(innerHTML);
                        $('#information').html(innerHTML);
                    }
                },
                error  : function () {
                    alert('请求失败')
                }
            });
            return false;
        });

    });

    function showBarChart(data) {
        var type = data['type'];
        charData = [];
        var title_text;
        if (type == 0) {
            title_text = data['country'];
            for (var i = 0; i < data['data'].length; i++) {
                var column = {
                    type: "column",
                    name: data['country'] + data['time'][i] + '年的数据',
                    legendText: "" +  data['time'][i],
                    showInLegend: true,
                    dataPoints: [
                        {label: "政治风险", y: data['data'][i][0]},
                        {label: "经济风险", y: data['data'][i][1]},
                        {label: "社会风险", y: data['data'][i][2]},
                        {label: "综合风险", y: data['data'][i][3]},
                    ]
                };
                charData.push(column);
            }
        } else if (type == 1) {
            title_text = "各国家" + data['time'] + "年风险对比";
            for (var i = 0; i < data['data'].length; i++) {
                var column = {
                    type: "column",
                    name: data['country'][i] + data['time'] + '年的数据',
                    legendText: "" + data['country'][i],
                    showInLegend: true,
                    dataPoints: [
                        {label: "政治风险", y: data['data'][i][0]},
                        {label: "经济风险", y: data['data'][i][1]},
                        {label: "社会风险", y: data['data'][i][2]},
                        {label: "综合风险", y: data['data'][i][3]},
                    ]
                };
                charData.push(column);
            }

        }

        var chart = new CanvasJS.Chart("chartContainer", {
            backgroundColor: "#EEEEEE",
            animationEnabled: true,
            title:{
                text: title_text
            },
            toolTip: {
                shared: true
            },
            legend: {
                cursor:"pointer",
                itemclick: toggleDataSeries
            },
            data: charData,
        });
        chart.render();

        function toggleDataSeries(e) {
            if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                e.dataSeries.visible = false;
            }
            else {
                e.dataSeries.visible = true;
            }
            chart.render();
        }
    }

    function showLineChart(data) {
        var type = data['type'];
        charData = [];
        risk = ["政治风险","经济风险","社会风险","综合风险"];
        var title_text;
        if (type == 0) {
            title_text = data['country'];
            for (var i = 0; i < 4; i++) {
                dataPts = [];
                for (var j = 0; j < data['time'].length; j++) {
                    var pts = {x:new Date(data['time'][j],0,0),y:data['data'][j][i]};
                    dataPts.push(pts);
                }
                var line = {
                    type: "line",
                    name: risk[i],
                    showInLegend: true,
                    dataPoints: dataPts
                };
                charData.push(line);
            }
        } else if (type == 1) {
            for (var i = 0; i < 4; i++) {
                dataPts = [];
                for (var j = 0; j < data['country'].length; j++) {
                    // var pts = {x:new ()}
                }
                var column = {
                    type: "column",
                    name: data['country'][i] + data['time'] + '年的数据',
                    legendText: "" + data['country'][i],
                    showInLegend: true,
                    dataPoints: [
                        {label: "政治风险", y: data['data'][i][0]},
                        {label: "经济风险", y: data['data'][i][1]},
                        {label: "社会风险", y: data['data'][i][2]},
                        {label: "综合风险", y: data['data'][i][3]},
                    ]
                };
                charData.push(column);
            }

        }

        var chart = new CanvasJS.Chart("chartContainer", {
            backgroundColor: "#EEEEEE",
            title:{
                text: data['country']
            },
            axisX: {
                valueFormatString: "YYYY"
            },
            toolTip: {
                shared: true
            },
            legend: {
                cursor: "pointer",
                itemclick: toggleDataSeries
            },
            data: charData
        });
        chart.render();

        function toggleDataSeries(e) {
            if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                e.dataSeries.visible = false;
            } else {
                e.dataSeries.visible = true;
            }
            e.chart.render();
        }
    }


    // 点击水平导航栏“编辑”、“文件”、“文件审核”显示文件
    function doClickHorizontalMenu(isActive, isEdit) {
        if (isActive == "false" && isEdit == "true" && userInfo.level == 0) {
            location = '${ctx}/login';
        }
        clearVerticalMenuCSS();
        $("#isActive").val(isActive);
        $("#isEdit").val(isEdit);
        var affiliationId = String($("#verticalMenu > li:nth-child(1) > a").attr("id"));
        affiliationId = affiliationId.substr(15);
        doClickShowDoc(affiliationId, 1);
        $("#body-content-right").html("");
        if (isActive == "true" && isEdit == "false") {
            $("#adminMenu > dl").unbind("click").bind("click", function () {
                $("#adminMenu").addClass("layui-this");
            });
        }
    }

    // 切换水平导航栏时恢复垂直导航栏状态
    function clearVerticalMenuCSS() {
        var ele = $("#verticalMenu");
        ele.children("li").each(function (i, childEle) {
            $(childEle).removeClass("layui-nav-itemed");
            $(childEle).removeClass("layui-this");
        });
        ele.children("dd").each(function (i, childEle) {
            $(childEle).removeClass("layui-this");
        });
    }

    // 显示用户信息
    function checkUserLogin() {
        if (userInfo != null && userInfo.id != null && userInfo.id != -1) {
            $("#loginButton").hide();
            var html = "";
            html = html + '<a href="javascript:;">' + userInfo.name + '</a>'
                + '<dl class="layui-nav-child">'
                + '<dd><a href="${ctx}/updatePassword">修改密码</a></dd>'
                + '<dd style="text-align: center;"><a href="javascript:;" onclick="logout();">退出</a></dd>'
                + '</dl>';
            $("#userInfoButton").html(html);
            $("#userInfoButton").show();
        } else {
            $("#loginButton").show();
            $("#userInfoButton").hide();
        }

        if (userInfo != null && userInfo.level != null && userInfo.level == 3) {
            var html = "";
            html = html + '<a href="javascript:;">管理员</a>\n' +
                '                <dl class="layui-nav-child">\n' +
                '                    <dd><a href="javascript:;" onclick="doClickHorizontalMenu(\'true\', \'false\');">文献审核</a></dd>\n' +
                '                    <dd><a href="javascript:;" onclick="doClickShowUserManagement(1);">用户管理</a></dd>\n' +
                '                </dl>'
            $("#adminMenu").html(html);
        }
    }

    // 用户退出
    function logout() {
        $.ajax({
            type: "get",
            url: "${ctx}/reglogin/logout",
            success: function (data) {
                if (data.code != 200) {
                    layer.msg(data.msg, {icon: 2});
                    return false;
                } else {
                    layer.msg(data.msg, {icon: 1});
                    location = "${ctx}/";
                }
            }
        });
    }


    // 显示所有用户
    function doClickShowUserManagement(curPage) {
        var levelEnum = ["普通用户", "VIP用户", "管理员"];
        $.ajax({
            type: 'get',
            url: "${ctx}/reglogin/showAllUser",
            data: {"page": curPage},
            dataType: "json",
            success: function (data) {
                if (data.code != 200) {
                    layer.msg(data.msg, {icon: 2});
                    console.log(data.msg);
                    return false;
                } else {
                    var htmlName = '<div class="layui-form"><table class="layui-table"><thead><tr>' +
                        '<th style="width: 6%;text-align: center">序号</th>' +
                        '<th style="width: 6%;text-align: center">用户名</th>' +
                        '<th style="width: 42%;text-align: center"">邮箱</th>' +
                        '<th style="width: 8%;text-align: center"">级别</th>' +
                        '<th style="width: 10%;text-align: center"">操作</th>';
                    htmlName = htmlName + '</tr></thead>';
                    var pagination = data.data.pagination;
                    var userList = pagination.data;
                    var pageInfo = pagination.pageInfo;
                    var totalPage = pageInfo.totalPage;

                    if (userList.length > 0) htmlName = htmlName + '<tbody>';
                    var sequence = 1;
                    userList.forEach(function (element) {
                        var name = element.name;
                        var email = element.email;
                        var level = element.level;
                        htmlName = htmlName + '<tr>' +
                            '<td style="text-align: center;">' + sequence + '</td>' +
                            '<td style="text-align: center;">' + name + '</td>' +
                            '<td style="text-align: center;">' + email + '</td>' +
                            '<td style="text-align: center;">' + levelEnum[level - 1] + '</td>' +
                            '<td style="text-align: center;">';
                        if (level <= 1) {
                            htmlName = htmlName + '<a class="clickAction" href="javascript:;" onclick="doClickGrantVIP(' + element.id + ',' + curPage + ');">升级用户</a>';
                        } else {
                            htmlName = htmlName + '升级用户';
                        }
                        htmlName = htmlName + '</td></tr>';
                        sequence++;
                    });
                    if (userList.length > 0) htmlName = htmlName + '</tbody></table></div>';
                    $("#body-content-left").html(htmlName);

                    var htmlPage = '<li class="total-page"><a href="javascript:void(0);">共&nbsp;' + totalPage + '&nbsp;页</a></li>';
                    if (curPage <= 1) {
                        htmlPage = htmlPage + '<li><a href="javascript:void(0);">上一页</a></li>';
                    } else {
                        curPage = curPage - 1;
                        htmlPage = htmlPage + '<li><a href="javascript:void(0);" onclick="doClickShowUserManagement('
                            + curPage
                            + ')">上一页</a></li>';
                    }
                    htmlPage = htmlPage + '<li><a href="javascript:void(0);">当前页&nbsp;' + pageInfo.page + '</a></li>';
                    if (curPage >= pageInfo.totalPage) {
                        htmlPage = htmlPage + '<li><a href="javascript:void(0);">下一页</a></li>';
                    } else {
                        curPage = curPage + 1;
                        htmlPage = htmlPage + '<li><a href="javascript:void(0);" onclick="doClickShowUserManagement('
                            + curPage
                            + ')">下一页</a></li>';
                    }
                    $("#page").html(htmlPage);
                }
            }
        });
    }

    function clickBackBtn() {
        $("#blog-main-left").show();
        $("#blog-main-right").show();
        $("#showPdf").hide();
        $("#back").hide();
    }

    function timestampToTime(timestamp) {
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        return Y + M + D;
    }

    function timestampToDate(timestamp) {
        var date = new Date(timestamp);
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        var h = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
        var m = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
        var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
        return Y + M + D + h + ':' + m + ":" + s;
    }

    // 升级VIP用户操作
    function doClickGrantVIP(userId, curPage) {
        var e = e || window.event;
        var obj = e.target || e.srcElement;
        $.ajax({
            type: 'get',
            url: '${ctx}/reglogin/grantVIP',
            data: {"id": userId},
            dataType: "json",
            success: function (data) {
                if (data.code != 200) {
                    layer.msg(data.msg, {icon: 2});
                    return false;
                } else {
                    layer.msg(data.msg, {icon: 1});
                    doClickShowUserManagement(curPage);
                    return;
                }
            }
        });
    }


    var leftNavFlag = false;
    function showLeftNav() {
        if(leftNavFlag == false){
            $(".left-nav-index").show();
            leftNavFlag = true;
        }else{
            $(".left-nav-index").hide();
            leftNavFlag = false;
        }

    }

    // 二维码分享
    $(".share").unbind("click").bind('click', function () {
        var url = document.location;
        var qrcode = new QRCode('qrcode', {
            text: String(url),
            width: 220,
            height: 220,
            colorDark : '#000000',
            colorLight : '#ffffff',
            correctLevel : QRCode.CorrectLevel.H
        });
        layer.open({
            title: '分享此网页',
            type: 1,
            resize: false,
            area: ['280px', '280px'],
            content: $('#popQRCode'),
            cancel: function (index, layer0) {
                qrcode.clear();
                $('#qrcode').html('');
            }
        });
    });
</script>
</html>