import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Detail} from '../models/Detail.model';
import { Observable,throwError } from 'rxjs';
import {Transaction} from '../models/Transaction.model';
@Injectable({providedIn: 'root'})
export class ProjectService {
headers= new Headers({ 'Content-Type': 'application/json' });
private options={headers:this.headers};
currentUser=new Detail();
currBalance;
  private type;
	private userUrl= 'http://localhost:8080/api';
  constructor(private http:HttpClient) { }
  private loggedInStatus=JSON.parse(localStorage.getItem('loggedIn')||'false');
  get isLoggedIn()
  {
return JSON.parse(localStorage.getItem('loggedIn')|| this.loggedInStatus.toString());
  }
  setLoggedIn(value:boolean)
  {
    this.loggedInStatus=value;
    localStorage.setItem('loggedIn','true');

  }
setTransType(type)
{
this.type=type;
}
getTransType()
{
  return this.type;
}
getUserbyID(id:Number)
{
  return this.http.get(this.userUrl+'/account/'+id);
}
deposit(accountId:Number,money:String)
{
  return this.http.put(this.userUrl+'/account/'+accountId+'/deposit',money);
}
Withdraw(accountId:Number,money:String)
{
  return this.http.put(this.userUrl+'/account/'+accountId+'/withdraw',money);
}
getTransaction(accountId:Number)
{

}
updateAccount(customer: Detail) {
  return this.http.put(this.userUrl + '/account', customer);
}
deleteAccount(id: Number) {
  return this.http.delete(this.userUrl + '/account/' + id);
}
existUserById(id:Number)
{
  return this.http.get(this.userUrl+'/exist/'+id);
}
 getTransaction1(accountId:Number):Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.userUrl+'/account/'+accountId+'/printTransactions');
  }
 transferFund(senderId:Number, recieverId:Number,money:String)
{
 return this.http.put(this.userUrl+'/account/'+senderId+'/fundTransfer2/'+recieverId,money);
}
 transferFund1(senderId:Number, recieverId:Number,money:String)
{
 return this.http.put(this.userUrl+'/account/'+senderId+'/fundTransfer1/'+recieverId,money);
}
setCurrentUser(user){
    localStorage.setItem("user", JSON.stringify(user));
  
  }
getCurrentUser()
  {
  return localStorage.getItem("user");
  } 
setBalance(balance)
  {
    this.currBalance=balance;
  }
getBalance()
  {
    return this.currBalance;
  }
getUser(): Observable<Detail[]> {
    return this.http.get<Detail[]>(this.userUrl+"/fetchUserDetail");
  }
  public createUser(user:Object) {
    return this.http.post<Detail>(this.userUrl+"/add", user);
  }
}
