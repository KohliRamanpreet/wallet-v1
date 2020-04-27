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
    Flag:boolean=false;
    errorMessage;
    confirmPass;
    flag;
pin;
     constructor(private router: Router, private projectService: ProjectService){}
    ngOnInit(){}
   createUser(submitForm): void {
       if((this.confirmPass==this.user.pass)&&(this.pin==this.user.pin))
       {
       this.projectService.createUser(this.user)
            .subscribe( data => this.gotoSuccess());
       }
       else
       {
           this.errorMessage="Enter Correct passowrd/PIN";
           this.flag=2;

       }
    
      }
      reSignUp()
      {
          this.Flag=false;
          this.flag=0;
          this.router.navigate(['/signup']);

      } 
      validateUser()
      {
        this.Flag=true;

      }
gotoSuccess()
{
 this.router.navigate(['']);

}
}