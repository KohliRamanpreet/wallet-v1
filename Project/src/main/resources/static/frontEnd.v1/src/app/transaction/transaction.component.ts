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
  constructor(private _projectService:ProjectService,private _router:Router) { 




  }

  ngOnInit(): void {
  this.user=this._projectService.getCurrentUser();
  this.transType=this._projectService.getTransType();
 

}
proceedDeposit (depositForm) {
    this.money = depositForm.value.money;
    this.negativeAmount = false;
    if (this.money > 0) {
      this.transFlag = true;
    } else {
      this.negativeAmount = true;
    }
  }

  deposit(depositForm) {
    if (depositForm.value.pin == "this.user.pin") {
      this._projectService.deposit(this.user.accountId, this.money).subscribe();
    //  setTimeout(() => {
        this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => {
          this._projectService.setCurrentUser(customer);
          this.user1=this._projectService.getCurrentUser();
          this._projectService.setBalance(this.user1.balance);
        });
     // }, 4000);
      this._router.navigate(['../successpage']);
    } else {
      alert("Incorrect Pin")
    }
  }
  proceedWithdraw (withdrawForm) {
    this.money = withdrawForm.value.money;
    this.negativeAmount = false;
    if (this.money > 0) {
      this.transFlag = true;
    } else {
      this.negativeAmount = true;
    }
  }

  withdraw(withdrawForm) {
    if (withdrawForm.value.pin == this.user.pin) {
      this._projectService.Withdraw(this.user.accountId, this.money).subscribe();
    // setTimeout(() => {
        this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => {
          this._projectService.setCurrentUser(customer);
this.user1=this._projectService.getCurrentUser();
          this._projectService.setBalance(this.user1.balance);
        });
     // }, 4000);
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
      this.transFlag = true;
    } else {
      this.negativeAmount = true;
    }
    this.check=false;

  }

  }
  fundTransfer(withdrawForm) {

    if (withdrawForm.value.pin == this.user.pin) {
      this._projectService.transferFund(this.user.accountId,this.recieverId, this.money).subscribe();
     // this._projectService.transferFund1(this.user.accountId,this.recieverId, this.money).subscribe();

    // setTimeout(() => {
        this._projectService.getUserbyID(this.user.accountId).subscribe((customer) => {
          this._projectService.setCurrentUser(customer);
          this.user1=this._projectService.getCurrentUser();
          this._projectService.setBalance(this.user1.balance);
        });
     // }, 4000);
      this._router.navigate(['../successpage']);
    } else {
      alert("Incorrect Pin")
    }
  }

}