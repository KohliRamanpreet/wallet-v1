import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Detail} from '../models/Detail.model';
import { Observable } from 'rxjs';
import {Login} from '../models/Login.model';
import {Subject} from 'rxjs'
import {Transaction} from '../models/Transaction.model'


/*const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}; */

@Injectable({providedIn: 'root'})
export class ProjectService {
  //private _Profile=new Subject<Detail>();
currentUser=new Detail();
  
	private userUrl= 'http://localhost:8080/api';
  constructor(private http:HttpClient) { }

setCurrentUser(user:Detail){
 // this._Profile.next(user);
  //this.profile$=this._Profile.asObservable();
  this.currentUser=user;
  

}
getCurrentUser()
{
return this.currentUser;
} 
 getUser(): Observable<Detail[]> {
    return this.http.get<Detail[]>(this.userUrl+"/fetchUserDetail");
  }
  public getTransaction():Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.userUrl+"/fetchtransaction");
  }

 /* public deleteUser(user) {
    return this.http.delete(this.userUrl + "/"+ user.id);
  } */

  public createUser(user:Object) {
    return this.http.post<Detail>(this.userUrl+"/add", user);
  }


}
