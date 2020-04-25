import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import {Http,Response,Headers,RequestOptions} from '@angular/common/http';

import { Detail} from '../models/Detail.model';
import { Observable,throwError } from 'rxjs';
import {Login} from '../models/Login.model';
import {Subject} from 'rxjs'
import {Transaction} from '../models/Transaction.model';
import {map,catchError} from 'rxjs/operators';


/*const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}; */

@Injectable({providedIn: 'root'})
export class ProjectService {
  headers= new Headers({ 'Content-Type': 'application/json' });
  private options={headers:this.headers};
currentUser=new Detail();
currBalance;
  private type;
	private userUrl= 'http://localhost:8080/api';
  constructor(private http:HttpClient) { }


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
  //.pipe(map((response:
    //Response)=>response.json()));
}
Withdraw(accountId:Number,money:String)
{
  return this.http.put(this.userUrl+'/account/'+accountId+'/withdraw',money);
}
getTransaction(accountId:Number)
{

}
//errorHandler(error:Response){
  //retrun Observable.throw(error||'SERVER ERROR');
//}




existUserById(id:Number)
{
  return this.http.get(this.userUrl+'/exist/'+id);
}

  public getTransaction1(accountId:Number):Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.userUrl+'/account/'+accountId+'/printTransactions');
  }

public transferFund(senderId:Number, recieverId:Number,money:String)
{
 return this.http.put(this.userUrl+'/account/'+senderId+'/fundTransfer2/'+recieverId,money);
}
public transferFund1(senderId:Number, recieverId:Number,money:String)
{
 return this.http.put(this.userUrl+'/account/'+senderId+'/fundTransfer1/'+recieverId,money);
}
  setCurrentUser(user){
    this.currentUser=user;
  
  }
  getCurrentUser()
  {
  return this.currentUser;
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
