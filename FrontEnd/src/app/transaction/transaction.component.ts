import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {ProjectService} from '../services/project.service';
import {Detail} from '../models/Detail.model';
import { Transaction } from '../models/Transaction.model';
 
@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

transFlag=false;
transType;
money;
user=new Detail();
transactionPin;
negativeAmount:Boolean;
wrongPassword;
recieverId;
check;
errorMessage;
user1=new Detail();
user2;
  constructor(private _projectService:ProjectService,private _router:Router) { }
  logOut()
      {
        this._projectService.setLoggedIn(false);
        localStorage.removeItem("user");
        this._router.navigate(['']);

      }

  ngOnInit(): void {
  this.transType=this._projectService.getTransType();
  this.user2=this._projectService.getCurrentUser();
    this.user=JSON.parse(this.user2);
    console.log(this.user);
 

}
proceedDeposit (depositForm) {
    this.money = depositForm.value.money;
    this.negativeAmount = false;
    if (this.money > 0) {
      this.transFlag = true;
      this.errorMessage="";
    } else {
      this.negativeAmount = true;
      this.errorMessage="Cannot Deposit negative Amount";
    }
  }

  deposit(depositForm) {

    if (depositForm.value.pin == this.user.pin) {
      this._projectService.deposit(this.user.accountId, this.money).subscribe();
     alert("Money Deposited Successfully!");
      this._router.navigate(['../successpage']);
    } else {
      alert("Incorrect Pin")
    }
  }
  proceedWithdraw (withdrawForm) {
    this.money = withdrawForm.value.money;
    this.negativeAmount = false;
    if (this.money > 0 )  {
      if(this.money>this.user.balance)
      {
        this.negativeAmount = true;
      this.errorMessage="Insufficient fund";
      }
      else
      {this.errorMessage="";
      this.transFlag = true;
    }

    }
    
    else {
      this.negativeAmount = true;
      this.errorMessage="Cannot withdraw negative amount";
    }
  }
  withdraw(withdrawForm) {
    if (withdrawForm.value.pin == this.user.pin) {
      this._projectService.Withdraw(this.user.accountId, this.money).subscribe();
        alert("Money Withdrawn Successfully!");
      this._router.navigate(['../successpage']);
    } else {
      alert("Incorrect Pin")
    }
  }
  proceedFundTransfer(fundForm)
  {
    this.money = fundForm.value.money;
    this.recieverId=fundForm.value.rId;
    if(fundForm.value.rId==fundForm.value.rRId)
   {
this._projectService.existUserById(this.recieverId).subscribe((exists)=>{
  this.check=exists;
});

   }
   else
   {
     this.errorMessage="Re-Enter Similar Ids";

   }
   if(this.check==true)
   {
     this.errorMessage="";
    this.negativeAmount = false;
    if (this.money > 0) {
      if(this.user.balance<this.money)
      {
        this.negativeAmount=true;
        this.errorMessage="Insufficient fund";
      }
      else{
      this.transFlag = true;
      this.errorMessage="";
      }
    } else {
      this.negativeAmount = true;
      this.errorMessage="cannot send negative amount";
    }
    this.check=false;

  }
  else
  {
    this.errorMessage="This Id does not exist"
  }

  }
  edit(form)
  {
    console.log(this.user);
    this._projectService.updateAccount(this.user).subscribe();
    this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => {
      localStorage.removeItem("user");
      this._projectService.setCurrentUser(customer);
      this.user2=this._projectService.getCurrentUser();
      this.user=JSON.parse(this.user2); 
    });
    alert("Profile Updated Successfully!");
      this._router.navigate(['../successpage']);
    
  }
  fundTransfer(withdrawForm) {

    if (withdrawForm.value.pin == this.user.pin) {
      this._projectService.transferFund(this.user.accountId,this.recieverId, this.money).subscribe();
        alert("Fund Transfered Successfully!");
      this._router.navigate(['../successpage']);
    } else {
      alert("Incorrect Pin")
    }
  }
  delete()
  {
    this._projectService.deleteAccount(this.user.accountId).subscribe();
    this._router.navigate(['']);
    localStorage.removeItem("user");

  }

}