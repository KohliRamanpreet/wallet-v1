import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {ProjectService} from '../services/project.service';
import {Transaction} from '../models/Transaction.model'
import { Detail } from '../models/Detail.model';


@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {


user=new Detail();
user1=new Detail();
trans:Transaction[];
showBalance:boolean;
confirmUser=new Transaction();
  constructor(private _router:Router, private _projectService:ProjectService) { 

    this._router.routeReuseStrategy.shouldReuseRoute=function()
    {
        return false;
    }

  }

  ngOnInit(): void {
    this.user=this._projectService.getCurrentUser();
    this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => { this._projectService.setCurrentUser(customer) }
);
this.user1=this._projectService.getCurrentUser();
}
}