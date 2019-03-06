import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user.service'
import { ActivatedRoute } from "@angular/router";
import { PersonResponse } from '../interfaces';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  curPage: string;
  user:Object;
  personInfo: PersonResponse;

  constructor(private userService: UserService, private route: ActivatedRoute) { 
    this.route.params.subscribe(params => this.user = params.nickname);
  }

  ngOnInit() {
    this.curPage = localStorage.getItem('curPage');
     this.userService.getUser(this.user).subscribe(
       userService => this.personInfo = userService
     );
     console.log(this.personInfo.description);
  }

}
