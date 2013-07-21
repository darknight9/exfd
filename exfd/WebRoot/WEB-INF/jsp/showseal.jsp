<!--首先导入一些必要的包-->
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <TITLE>Show the Seal Information.</TITLE>
</head>
<body>
<%
try{
  Connection con;
  Statement stmt;
  ResultSet rs;
 
  //建立数据库连接
  Context ctx = new InitialContext();
  DataSource ds =(DataSource)ctx.lookup("java:comp/env/jdbc/SealDB");
  con = ds.getConnection();

  //创建一个SQL声明
  stmt = con.createStatement();
  //增加新记录
  //stmt.executeUpdate("insert into SEALINFO values ('temp001', '1', '2013-07-01 00:00:00', '331.111', '122.222', '0', '备注TemP')" );

  //查询记录
  rs = stmt.executeQuery("select SID,STATUS,PRODUCE_TIME,LONGITUDE,LATITUDE,FLAG,DESCRIPTION from SEALINFO");

  //输出查询结果
  out.println("<table border=1 width=400>");
  while (rs.next()){
    String sid = rs.getString(1);
    int status = rs.getInt(2);
    java.util.Date produceTime = rs.getTimestamp(3);
    double longitude = rs.getDouble(4);
    double latitude = rs.getDouble(5);
    int flag = rs.getInt(6);
    String description = rs.getString(7);
				  
    //转换字符编码
    //col1=new String(col1.getBytes("ISO-8859-1"),"GB2312");

    //打印所显示的数据
    out.println("<tr><td>"+sid+"</td><td>"+status+"</td><td>"+produceTime+"</td><td>"+longitude+"</td><td>"+latitude+"</td><td>"+flag+"</td><td>"+description+"</td></tr>");
  }

  out.println("</table>");

  //删除新增加的记录
  //stmt.executeUpdate("delete from SEALINFO where SID='temp001'");

  //关闭结果集、SQL声明、数据库连接
  rs.close();
  stmt.close();
  con.close();
}catch (Exception e) {out.println(e.getMessage());e.printStackTrace();}

%>
</body>
</html>
