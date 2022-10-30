
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.ArrayList;

class ErrorMessage {
  void chainBroken() {
    System.out.println("[!] Chain Broken");
  }

  void optionNotFound() {
    System.out.println("[!] Option not found");
  }
}

class NormalMessage {
  void chainValid() {
    System.out.println("[*] Chain valid");
  }

  void blockCreated(int a) {
    System.out.printf("\r[+%d] Block Created.  \n", a);
  }

  void blockCreating(int a) {
    System.out.printf("\r[@%d] Creating..", a);
  }

  void genisisCreated() {
    System.out.println("\r[+0] Genisis Block created");
  }

  void printMenu() {
    System.out.printf("1> create Block\n2> show chain\n3> check Relaiyable\n4> Quit\n>option: ");
  }

  void addBlockPrompt() {
    System.out.printf("Enter the data: ");
  }
}

class Block {
  int index;
  String time;
  String data;
  String hash;
  String previous;
  int nons;
  private int complex;

  Block(int index, String data) {
    this.index = index;
    this.data = data;
    this.hash = "";
    this.previous = "";
    this.nons = -1;
    this.complex = 4;
  }

  public String genHash() {
    String hash_ = "";
    String data = "";
    String nonesData = "";
    for (int i = 0; i < complex; i++, nonesData += "f")
      ;
    do {
      System.out.printf("⚒ [@%d] Creating..\r", this.nons++);
      try {
        this.time = new Date().toString();
        data = "" + this.index + this.time + this.data + this.previous + this.nons;
        MessageDigest m = MessageDigest.getInstance("SHA-1");
        byte[] md = m.digest(data.getBytes());
        BigInteger bin = new BigInteger(1, md);
        hash_ = bin.toString(16);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } while (!hash_.substring(0, this.complex).equals(nonesData));
    return hash_;
  }
}

class Chain {
  ArrayList<Block> chain = new ArrayList<Block>();
  private ErrorMessage em = new ErrorMessage();
  private NormalMessage nm = new NormalMessage();
  int chainLength;
  String chainName;

  Chain(String chainName) {
    this.chainName = chainName;
    this.chainLength = 0;
  }

  private void printBlock(Block a) {
    System.out.printf(
        "{ \n\t'index': %d,\n\t'time': '%s',\n\t'data': '%s',\n\t'previous': '0x%s',\n\t'hash': '0x%s',\n\t'nons': %d\n}\n",
        a.index,
        a.time,
        a.data,
        a.previous,
        a.hash,
        a.nons);
  }

  void printChain() {
    for (int i = 0; i < chainLength; i++) {
      this.printBlock(chain.get(i));
    }
  }

  void createGeniciosBlock() {
    Block tmp = new Block(this.chainLength++, "This is " + this.chainName);
    tmp.previous = "0000000000000000000000000000000000000";
    tmp.hash = tmp.genHash();
    chain.add(tmp);
    nm.genisisCreated();
  }

  boolean checkChainRelaiablity() {
    for (int i = chainLength - 1; i > 0; i--) {
      if (chain.get(i).previous.equals(chain.get(i - 1).hash)) {
        continue;
      }
      em.chainBroken();
      return false;
    }
    nm.chainValid();
    return true;
  }

  void addBlock(String data) {
    if (this.checkChainRelaiablity()) {
      Block tmp = new Block(this.chainLength, data);
      tmp.previous = chain.get(chainLength - 1).hash;
      tmp.hash = tmp.genHash();
      chain.add(tmp);
      nm.blockCreated(chainLength);
      this.chainLength++;
    }
  }
}

public class SimpleBlockChain {
  public static void main(String args[]) {
    Scanner s = new Scanner(System.in);
    String o = "";
    ErrorMessage em = new ErrorMessage();
    NormalMessage nm = new NormalMessage();

    System.out.printf("Enter Chain Name:");
    Chain myChain = new Chain(s.nextLine());
    myChain.createGeniciosBlock();
    do {
      nm.printMenu();
      o = s.nextLine();
      if (o.equals("1")) {
        nm.addBlockPrompt();
        myChain.addBlock(s.nextLine());
      } else if (o.equals("2")) {
        myChain.printChain();
      } else if (o.equals("3")) {
        myChain.checkChainRelaiablity();
      } else if (o.equals("4")) {
        break;
      } else {
        em.optionNotFound();
      }
    } while (!o.equals("4"));
    s.close();
  }
}