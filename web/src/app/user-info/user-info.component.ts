import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user.service'
import { ActivatedRoute } from "@angular/router";
import { PersonResponse, ResponseState } from '../interfaces';
import { FriendsService } from '../shared/services/friends.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  isItYourPage: boolean;
  curPage: string;
  personInfo: PersonResponse;
  state: ResponseState;

  constructor(private userService: UserService, private friendService: FriendsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.curPage = localStorage.getItem('curPage');
    this.userService.getUser(this.curPage).subscribe(
      userService => this.personInfo = userService
    );
    if (localStorage.getItem('curPage') == localStorage.getItem('curNickname'))
      this.isItYourPage = true;
    else
      this.isItYourPage = false;

    console.log(this.isItYourPage);
  }

  addFriend(){
    this.friendService.addFriend(localStorage.getItem('curPage')).subscribe(
      friendService => this.state = friendService
    );
  }

}
