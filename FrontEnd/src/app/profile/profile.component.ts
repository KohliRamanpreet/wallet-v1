import { Component } from '@angular/core';
import { Detail } from '../models/Detail.model';
import {ProjectService} from '../services/project.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

    user=new Detail();
    user1;
    constructor(private _projectService:ProjectService,private router:Router)
    {}
    ngOnInit(): void {
        this.user1=this._projectService.getCurrentUser();
    this.user=JSON.parse(this.user1);
    console.log(this.user);
    this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => {
      localStorage.removeItem("user");
      this._projectService.setCurrentUser(customer);
      this.user1=this._projectService.getCurrentUser();
      this.user=JSON.parse(this.user1); 
      });
    }
}
