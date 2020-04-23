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
  constructor() { 


  }

  ngOnInit(): void {
    
  }

}