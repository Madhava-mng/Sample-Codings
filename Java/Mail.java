import java.util.*;
import java.lang.Math;
import java.lang.Integer;
import java.lang.Exception;

class Users {
  String name;
  String pass;
  ArrayList<Integer> inbox = new ArrayList<Integer>();
  ArrayList<Integer> sent = new ArrayList<Integer>();

  public void getData(){
    Scanner s = new Scanner(System.in);
    System.out.printf("Enter Name: ");
    this.name = s.nextLine();
    System.out.printf("Enter Password: ");
    this.pass = s.nextLine();
  }
}

class ErrorMessage{
  String c = "\033[31m";
  String n = "\033[0m";
  
  public void UserExiest(){System.out.println(c+"[!] User already exiest."+ this.n);}
  public void passwdIncorrect(){System.out.println(this.c+"[!] Password Incorrect."+ this.n);}
  public void userIncorrect(){System.out.println(this.c+"[!] User Incorrect."+ this.n);}
  public void userCreated(){System.out.println(this.c+"[!] User Created Successfully."+ this.n);}
  public void msgNotDeleted(){System.out.println(this.c+"[!] Mail Not Deleted."+ this.n);}
  public void invaliedOption(){System.out.println(this.c+"[!] Invalied Option."+ this.n);}
  public void loginFail(){System.out.println(this.c+"[!] Login failed." + this.n);}
}
class NormalMessage{
  public void successLogin(){System.out.println("[*] Login Successfully.");}
  public void msgDeleted(){System.out.println("[*] Mail Deleted.");}
  public void msgSent(){System.out.println("[*] Mail Sent.");}
}

class Mails {

  int id;
  String from;
  String to;
  String sub;
  String msg;

  Mails(String from, int id){
    this.from = from;
    this.id = id;
  }

  public void putData(){
    System.out.println("from: " + this.from + "@zmail.com");
    Scanner s = new Scanner(System.in);
    System.out.printf("to: ");
    this.to = s.nextLine();
    System.out.printf("Sub: ");
    this.sub = s.nextLine();
    System.out.printf("Msg: ");
    this.msg = s.nextLine();
  }
  public boolean valid(){
    if(this.from.equals(this.to)){
      return false;
    }
    if(this.sub.length() < 5 && this.sub.length() > 20){
      return false;
    }
    if(this.msg.length() < 1 && this.msg.length() > 100){
      return false;
    }
    return true;
  }
}

public class Mail extends Exception{
  static Users user[] = new Users[10];
  static Mails mails[] = new Mails[100];
  static ErrorMessage em = new ErrorMessage();
  static NormalMessage nm = new NormalMessage();
  static Scanner s = new Scanner(System.in);
  

  static int userCount = -1;
  static int mailCount = -1;
  static String whoami = "";
  static int whoamiIndex = -1;

  private static int mid(){
    return (int)(Math.random() * 10000000);
  }

  private static void createAccount(){
    Users u = new Users();
    u.getData();
    for(int i =0;i<=userCount;i++){
      if(user[i].name.equals(u.name)){
        em.UserExiest();
        return;
      }
    } 
    
    userCount++;
    user[userCount] = new Users();
    user[userCount].name  = u.name;
    user[userCount].pass  = u.pass;
    em.userCreated();
  }
  private static void loginAccount(){
    System.out.printf("Name: ");
    String name = s.nextLine();
    System.out.printf("Password: ");
    String pass = s.nextLine();
    for(int i=0;i<=userCount;i++){
      if(user[i].name.equals(name) && user[i].pass.equals(pass)){
        nm.successLogin();
        whoami = user[i].name;
        whoamiIndex = i;
        userMenu();
        return;
      }
    }
    em.loginFail();
  }

  public static void compose(){
    Mails tmp = new Mails(whoami, mid());
    tmp.putData();
    String[] a = tmp.to.split(",");
    for(int z=0;z<a.length;z++){
      tmp.to = a[z];
      tmp.id = mid();
      if(tmp.valid()){
        mailCount++;
        mails[mailCount] = new Mails(whoami, tmp.id);
        mails[mailCount].id = tmp.id; 
        mails[mailCount].from = tmp.from; 
        mails[mailCount].to = a[z]; 
        mails[mailCount].sub = tmp.sub; 
        mails[mailCount].msg = tmp.msg;
        boolean flag = true;
        for(int i=0;i<=userCount;i++){
          if(tmp.to.equals(user[i].name)){
            flag = false;
            break;
          }
        }
        if(flag){
          break;
        }
        if(tmp.valid()){
          for(int i=0;i<=userCount;i++){
            if(user[i].name.equals(whoami)){  
              user[i].sent.add(tmp.id);
            }
            if(user[i].name.equals(a[z])){
              user[i].inbox.add(tmp.id);
            }
          }
          nm.msgSent();
        }
      }
    }
  }

  public static void printMail(Mails a){
    System.out.printf("\n\033[32m+----> [ id: \033[31m%d\033[32m ]\n|\n|\tfrom: %s@zmail.com\n|\tto: %s@zmail.com\n|\tsub: %s\n|\tmessage: %s\n+---\033[0m\n", 
      a.id,a.from,a.to,a.sub,a.msg
    );
  }
  public static void showInbox(){
    for(int i:user[whoamiIndex].inbox){
      for(int j =0;j<=mailCount;j++){
        if(mails[j].id == i){
          printMail(mails[j]);
        }
      }
    }
  }
  public static void showSent(){
    for(int i:user[whoamiIndex].sent){
      for(int j =0;j<=mailCount;j++){
        if(mails[j].id == i){
          printMail(mails[j]);
        }
      }
    }
  }
  public static void deleteInbox(){
    System.out.printf("Message Id: ");
    int id = s.nextInt();
    s.nextLine();
    for(int i=0;i < user[whoamiIndex].inbox.size();i++){
      if(id == user[whoamiIndex].inbox.get(i)){
        nm.msgDeleted();
        user[whoamiIndex].inbox.remove(i);
      }
    }
  }
  public static void deleteSent(){
    System.out.printf("Message Id: ");
    int id = (int)s.nextInt();
    s.nextLine();
    for(int i=0;i < user[whoamiIndex].sent.size();i++){
      if(id == user[whoamiIndex].sent.get(i)){
        user[whoamiIndex].sent.remove(i);
        nm.msgDeleted();
      }
    }
  }
  public static void userMenu(){
    System.out.printf("\n\n +-=[\033[46m%s@zmail.com\033[0m]\n| \n[1] Compose\n[2] Inbox\n[3] Sent Mail\n[4] Delete Mails\n[5] Delete Sent message\n[6] Logout\n +-> option: ", whoami);
    String o = s.nextLine();
    switch(o){
      case "1":
      compose();
      break;
      case "2":
      showInbox();
      break;
      case "3":
      showSent();
      break;
      case "4":
      deleteInbox();
      break;
      case "5":
      deleteSent();
      break;
      case "6":
      whoami = "";
      return;
    }
    userMenu();
  }

  public static void main(String[] args){

    user[0] = new Users();
    user[0].name = "admin";
    user[0].pass = "admin";
    user[1] = new Users();
    user[1].name = "user1";
    user[1].pass = "user1";
    user[2] = new Users();
    user[2].name = "user2";
    user[2].pass = "user2";
    userCount+=3;

    String o = "";
    while(true){
      System.out.printf("\n +-=[Main Menu]\n |\n[1] Create Account\n[2] Login Account\n[3] Exit\n |\n +-> Option: ");
      if(s.hasNextLine()){
        o = s.nextLine();
        switch(o){
          case "1":createAccount();break;
          case "2":loginAccount();break;
          case "3":return;
          default:em.invaliedOption();break;
        }
      }
    }
  }
}