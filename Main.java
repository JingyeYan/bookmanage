import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.internal.OracleTypes;

public class Main {
    //数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
            String username = "emp";
            String password = "oracle";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //查询表信息
    public static void getreader() {
        String sql="select * from reader";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("读者id 读者名字 读者电话");
            while(resultSet.next()) {
                String readno=resultSet.getString("readno");
                String readname=resultSet.getString("readname");
                String readtel=resultSet.getString("readtel");
                System.out.println(readno+" "+readname+" "+readtel);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void getbook() {
        String sql="select * from book";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("书号 书名 作者 出版社 书的数量");
            while(resultSet.next()) {
                String bookno=resultSet.getString("bookno");
                String bookname=resultSet.getString("bookname");
                String author=resultSet.getString("author");
                String pubname=resultSet.getString("pubname");
                int num=resultSet.getInt("num");
                System.out.println(bookno+" "+bookname+" "+author+" "+pubname+" "+num);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void getpubment() {
        String sql="select * from pubment";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("出版社名   出版社地址     出版社电话");
            while(resultSet.next()) {
                String pubadd=resultSet.getString("pubadd");
                String pubname=resultSet.getString("pubname");
                String pubtel=resultSet.getString("pubtel");
                System.out.println(pubname+" "+pubadd+" "+pubtel);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void getpub() {
        String sql="select * from pub";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("出版社名   出版社时间    书名");
            while(resultSet.next()) {
                String pubname=resultSet.getString("pubname");
                String pubtime=resultSet.getString("pubtime");
                String bookname=resultSet.getString("bookname");
                System.out.println(pubname+" "+pubtime+" "+bookname);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void getborrow() {
        String sql="select * from borrow";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("借阅的书号 借阅者id 借书时间  还书时间");
            while(resultSet.next()) {
                String bookno=resultSet.getString("bookno");
                String readno=resultSet.getString("readno");
                String borrowtime=resultSet.getString("borrowtime");
                String returntime=resultSet.getString("returntime");
                System.out.println(bookno+" "+readno+" "+borrowtime+" "+returntime);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //统计信息
    public  static void booknum(){
        String sql="select sum(num) from book";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("书库中的书总数为"+resultSet.getInt("sum(num)"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  static void bookkindnum(){
        String sql="select count(*) from book";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("书库中的书种类数量为"+resultSet.getInt("count(*)"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  static void readernum(){
        String sql="select count(*) from reader";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("已登记的读者数量是"+resultSet.getInt("count(*)"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //添加对象
    public static void addbook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入书号：");
        String bookno=sc.next();
        System.out.println("请输入书名：");
        String bookname=sc.next();
        System.out.println("请输入作者：");
        String author=sc.next();
        System.out.println("请输入出版社：");
        String pubname=sc.next();
        System.out.println("请输入书籍数量：");
        String num=sc.next();
            String sql="insert into book values('"+bookno+"','"+bookname+"','"+author+"','"+pubname+"',"+num+")";
            Connection connection=getConnection();
            try{
                Statement statement=connection.createStatement();
                ResultSet resultSet= statement.executeQuery(sql);
                System.out.println("插入书籍信息成功！");
            }catch (SQLException e){
                e.printStackTrace();
            }

    }
    public static void addreader(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入读者id：");
        String readno=sc.next();
        System.out.println("请输入读者名字：");
        String readname=sc.next();
        System.out.println("请输入读者联系方式：");
        String readtel=sc.next();
        String sql="insert into reader values('"+readno+"','"+readname+"','"+readtel+"')";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("插入读者信息成功！");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    //删除对象
    public static void dropbook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要删除的书籍书号：");
        String bookno=sc.next();
        String sql="delete from book where bookno='"+bookno+"'";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("删除成功！");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void dropreader(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要删除的读者id：");
        String readno=sc.next();
        String sql="delete from reader where readno='"+readno+"'";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("删除成功！");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //查询书籍
    public static void book_find(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要查询的书籍书号：");
        String bookno=sc.next();
        String sql = "{call book_find(?,?)}";
        Connection conn =getConnection();
        CallableStatement call = null;
        try {
            call = conn.prepareCall(sql);
            call.setString(1,bookno);
            call.registerOutParameter(2, OracleTypes.VARCHAR);
            call.execute();
            String bookName = call.getString(2);
            System.out.println("书名："+bookName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //借阅
    public static void addborrow(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入借阅者id：");
        String readno=sc.next();
        System.out.println("请输入书号：");
        String bookno=sc.next();
        System.out.println("请输入借阅时间：");
        String borrowtime=sc.next();
        System.out.println("请输入还书时间：");
        String returntime=sc.next();
        String sql="insert into borrow values('"+bookno+"','"+readno+"','"+borrowtime+"','"+returntime+"')";
        Connection connection=getConnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            System.out.println("借阅成功！");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        System.out.println("    图书管理系统     ");
        System.out.println("    1.查询信息      ");
        System.out.println("    2.统计信息     ");
        System.out.println("    3.增删对象     ");
        System.out.println("    4.查询书籍     ");
        System.out.println("    5.借阅书籍     ");
        System.out.println(" 请输入您要选择的功能序号： ");
        String x,y;
        Scanner sc = new Scanner(System.in);
        while (true) {
            x = sc.next();
            switch (x) {
                case "1":{
                    System.out.println("    1.查询书籍信息      ");
                    System.out.println("    2.查询出版社信息     ");
                    System.out.println("    3.查询读者信息     ");
                    System.out.println("    4.查询出版信息     ");
                    System.out.println("    5.查询借阅信息     ");
                    y=sc.next();
                    switch(y){
                        case "1":getbook();break;
                        case "2":getpubment();break;
                        case "3":getreader();break;
                        case "4":getpub();break;
                        case "5":getborrow();break;
                        default:System.out.println("无此功能请重新输入！");break;
                    }
                    break;}
                case "2": {
                    bookkindnum();
                    booknum();
                    readernum();
                    break;
                }
                case "3": {
                    System.out.println("    1.添加书籍      ");
                    System.out.println("    2.删除书籍     ");
                    System.out.println("    3.添加读者信息     ");
                    System.out.println("    4.删除读者信息     ");
                    y=sc.next();
                    switch(y){
                        case "1":addbook();break;
                        case "2":dropbook();break;
                        case "3":addreader();break;
                        case "4":dropreader();break;
                        default:System.out.println("无此功能请重新输入！");break;
                    }
                    break;
                }
                case "4":
                    book_find();
                    break;
                case "5":
                    addborrow();
                    break;
                default:
                    System.out.println("无此功能请重新输入！");
                    break;
            }
        }
    }
}