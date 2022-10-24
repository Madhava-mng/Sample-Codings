from random import randint as _id
from time import time as _time
from hashlib import md5 as _md5

data = {
        "admin": {
            "pass": "admin",
            "msg" : ["10001"],
            "sent": [],
            },
        "user1": {
            "pass": "user1",
            "msg" : [],
            "sent": ["10001"],
            }
    }

msgs = {
        "10001" : {'from': "user1", 'to': 'admin', 'sub':'test sub', 'msg': 'info hear\n'}
    }

whoLoginNow = []


getId = lambda:_md5(( str(_time()) + str(_id(1,50000)) ).encode('utf-8')).hexdigest()[0:10]

def checkMessageToDelete(_id):
    pass

def create():
    name = input("UserName: ").strip()
    passwd = input("Choose Strong Password: ").strip()
    if(name in list(data.keys())):
        print("[!] User Name already exiest, try another one")
        create();
    else:
        data[name] = {"pass":passwd,"msg":[],"sent":[]}
        print("[*] User Created\n[!] Please login")
        login()



def login():
    name = input("UserName: ").strip()
    passwd = input("Password: ").strip()
    if(name in list(data.keys())):
        print(f"[@] You loged in as {name}@z.com")
        whoLoginNow.append(name)
        userMainMenu()
    else:
        print("[!] User Name/Password is Incorrect.\n\tpls try again.")
        login()

def userMainMenu():
    if(whoLoginNow == []):
        return main()
    print("[1] Compose\n[2] List Mails\n[3] List Sent Mail\n[4] Delete Mail\n[5] Delete Sent Mail\n[6] Logout")
    o = int(input("Option: "))
    if(o == 1):
        compose()
    elif(o == 2):
        listMail()
    elif(o == 3):
        listSentMail()
    elif(o == 4):
        deleteMail()
    elif(o == 5):
        deleteSentMail()
    elif(o == 6):
        logout()
        return 1

    userMainMenu()



def compose():
    if(not whoLoginNow):
        return main()

    f = whoLoginNow[-1]
    t = input("Send to : ").split(',')
    s = input("Subject [length: 5-25]: ")
    msg = ''
    tmsg = input("[*] Note: enter -1 to stop typing\nWrite you Message Hear [length: 1-200]: ")

    while(tmsg != '-1'):
        msg += tmsg + '\n'
        tmsg = input("> ")

    for it in t:
        mid = getId()
        if(it != f):
            if( it in list(data.keys())):
                if(len(s) >=5 and len(s) <= 25):
                    if(len(msg) >= 1 and len(msg) <= 200):
                        data[f]['sent'].append(mid)
                        data[it]['msg'].append(mid)
                        msgs[mid] = {'from': f, 'to': it, 'sub':s, 'msg': msg}
                        print(f"[*] Msg Sent to {it}@z.com")
                    else:
                        print("[!] Msg length must be have length between 1-200");
                else:
                    print("[!] Subject length must be have length between 5-25");
            else:
                print("[!] User Not found")
        else:
            print("Mail not sent to you")

def listMail():
    if(not whoLoginNow):
        return main()
    print("-"*50)
    for i in data[whoLoginNow[-1]]['msg']:
        pt = msgs[i]
        print("From: " + pt['from'] + '@z.com')
        print("To: " + pt['to'] + '@z.com')
        print("Subject: " + pt['sub'])
        print("Message: " + pt['msg'])
    print("-"*50)


def listSentMail():
    if(not whoLoginNow):
        return main()
    print("-"*50)
    for i in data[whoLoginNow[-1]]['sent']:
        pt = msgs[i]
        print("From: " + pt['from'] + '@z.com')
        print("To: " + pt['to'] + '@z.com')
        print("Subject: " + pt['sub'])
        print("Message: " + pt['msg'])
    print("-"*50)



def deleteMail():
    if(not whoLoginNow):
        return main()
    who = whoLoginNow[-1]
    for i in data[who]['msg']:
        pt = msgs[i]
        print("Track Id: "+ i + " ----------")
        print( "From: " + pt['from'] + '@z.com')
        print("To: " + pt['to'] + '@z.com')
        print("Subject: " + pt['sub'])
        print("Message: " + pt['msg'])
    _id = input("Please Enter Id to Delete: ")
    I = 0
    for i in data[who]['msg']:
        if(i == _id):
            del data[who]['msg'][I]
            print("[*] Success fully deleted")
            checkMessageToDelete(_id)
        else:
            print("[!] Message id not match")
    I += 1


def deleteSentMail():

    if(not whoLoginNow):
        return main()
    who = whoLoginNow[-1]
    for i in data[who]['sent']:
        pt = msgs[i]
        print("Track Id: "+ i + " ----------")
        print( "From: " + pt['from'] + '@z.com')
        print("To: " + pt['to'] + '@z.com')
        print("Subject: " + pt['sub'])
        print("Message: " + pt['msg'])
    _id = input("Please Enter Id to Delete: ")

    I = 0
    for i in data[who]['sent']:
        if(i == _id):
            del data[who]['sent'][I]
            print("[*] Success fully deleted")
            checkMessageToDelete(_id)
        else:
            print("[!] Message id not match")
    I += 1

def logout():
    whoLoginNow = []


def main():
    print("[1] New Account\n[2] Login\n[3] Exit")
    o = int(input("Option: "))
    if(o == 1):
        create()
    elif(o == 2):
        login()
    elif(o == 3):
        return 0
    main()
if __name__ == '__main__':
  main()