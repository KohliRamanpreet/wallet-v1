import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {ProjectService} from '../services/project.service';
import {Transaction} from '../models/Transaction.model';
import { Detail } from '../models/Detail.model';
 

@Component({
  selector: 'app-print',
  templateUrl: './printtransaction.component.html',
  styleUrls: ['./printtransaction.component.css']
})
export class PrintComponent implements OnInit {

trans:Transaction[];
user=new Detail();
user1;

  constructor(private _projectService:ProjectService,private _router:Router) { 
      this.user1=this._projectService.getCurrentUser();
this.user=JSON.parse(this.user1);
this._projectService.getTransaction1(this.user.accountId).subscribe(trans => this.trans=trans);


  }
  logOut()
      {
        this._projectService.setLoggedIn(false);
        localStorage.removeItem("user");
        this._router.navigate(['']);

      }

  ngOnInit(): void {

  
  }
 
}