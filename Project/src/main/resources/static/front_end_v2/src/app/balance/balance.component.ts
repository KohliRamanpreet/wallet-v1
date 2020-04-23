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


user1: Transaction[];
user2=new Detail();
id1;
id2;
confirmUser=new Transaction();
  constructor(private _router:Router, private _projectservice:ProjectService) { 


  }

  ngOnInit(): void {
      this._projectservice.getTransaction().subscribe(data=> this.user1=data);
      this.user2=this._projectservice.getCurrentUser();
     /* for(let a of this.user1)
    {
if(a.user.userName==this.user2.userName)
{
  this.confirmUser=a;
  break;

}
      
    
  }*/

    

  }

  


}