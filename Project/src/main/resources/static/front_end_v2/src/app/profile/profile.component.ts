import { Component } from '@angular/core';
import { Detail } from '../models/Detail.model';
import {ProjectService} from '../services/project.service'

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

    user=new Detail();
    constructor(private _projectservice:ProjectService)
    {}
    ngOnInit(): void {
        this.user=this._projectservice.getCurrentUser();
        this._projectservice.setCurrentUser(this.user);
      }
      
}
