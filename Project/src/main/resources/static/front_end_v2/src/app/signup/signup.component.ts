import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../services/project.service';
import {Detail} from '../models/Detail.model';
import {Router} from '@angular/router'
@Component({
    selector: 'app-signup',
    templateUrl: `./signup.component.html`,
    styleUrls :['./signup.component.css']
})

export class SignupComponent implements OnInit{
    user:Detail=new Detail();

     constructor(private router: Router, private projectService: ProjectService){}
    ngOnInit(){}
   createUser(): void {
       this.projectService.createUser(this.user)
            .subscribe( data => this.gotoSuccess());
    
      }; 
gotoSuccess()
{
 this.router.navigate(['']);

}
}