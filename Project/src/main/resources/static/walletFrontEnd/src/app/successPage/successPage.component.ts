import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {Detail} from '../models/Detail.model';
import {ProjectService} from '../services/project.service'


@Component({
  selector: 'app-successpage',
  templateUrl: './successPage.component.html',
  styleUrls: ['./successPage.component.css']
})
export class SuccessPageComponent implements OnInit {
user=new Detail();
transType;

  constructor(private router:Router, private _projectService:ProjectService) { 
    this.user=this._projectService.getCurrentUser();


  }
onClickAdd()
{
  this.transType=1;
  this._projectService.setTransType(this.transType);
  this.router.navigate(['/transaction']);
}
onClickWithdraw()
  {
    this.transType=2;
    console.log("Hii");
  this._projectService.setTransType(this.transType);
  this.router.navigate(['/transaction']);
  }
  onClickTransfer()
  {
    this.transType=3;
    this._projectService.setTransType(this.transType);
  this.router.navigate(['/transaction']);
  

  }
  
  onClickEdit()
  {
    this.transType=4;
    this._projectService.setTransType(this.transType);
    this.router.navigate(['/transaction']);
  }
onClickDelete()
{
 this.transType=5;
 this._projectService.setTransType(this.transType);
 this.router.navigate(['/transaction']);

}
  ngOnInit(): void {
    
  }

}