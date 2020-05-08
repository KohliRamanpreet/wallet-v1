import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../models/Login.model';
import { ProjectService } from '../services/project.service';
import { Detail } from '../models/Detail.model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class loginComponent implements OnInit {

  user2: Detail[]
  username;
  password;
  id1;
  id2;
  confirmUser = new Detail();
errorMessage;
  constructor(private _router: Router, private _projectservice: ProjectService) {


  }

  ngOnInit(): void {
    this._projectservice.getUser().subscribe(data => this.user2 = data);
  }
  onSubmit() {
    for (let a of this.user2) {
      if (a.userName == this.username) {
        this.confirmUser = a;
        this.id1 = a.pNumber;
      }
      if (a.pass == this.password)
        this.id2 = a.pNumber;
    }
    if (this.id1 == this.id2 && this.id1 != null) {
      this._projectservice.setCurrentUser(this.confirmUser);
      this._projectservice.setBalance(this.confirmUser.balance);
      this.errorMessage="";
      
     this._router.navigate(['/successpage']);

    }
    else if (this.id1 == null)
      this.errorMessage="Invalid Credentials";
    else
    this.errorMessage="Invalid Credentials";
      



  }

}