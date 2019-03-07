import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FriendsService } from '../shared/services/friends.service';
import { FriendResponse } from '../interfaces';

@Component({
  selector: 'app-user-friends',
  templateUrl: './user-friends.component.html',
  styleUrls: ['./user-friends.component.css']
})
export class UserFriendsComponent implements OnInit {

  isItYourPage: boolean;
  currentNickname: string;
  curPage: string;
  friendResponse: FriendResponse;
  requestResponse: FriendResponse;

  constructor(private router: Router, private friendService: FriendsService) { }

  ngOnInit() {
    this.curPage = localStorage.getItem('curPage');
    this.currentNickname = localStorage.getItem('curNickname');

    if (this.currentNickname == this.curPage)
      this.isItYourPage = true;
    else
      this.isItYourPage = false;

    this.friendService.getFriends(this.curPage).subscribe(
      friendService => this.friendResponse = friendService
    );

    if (this.isItYourPage == true)
      this.friendService.getRequests().subscribe(
        friendService => this.requestResponse = friendService
      );

  }

  public acceptRequest(nickname) {
    return this.friendService.acceptRequest(nickname);
  }

  public declineRequest(nickname) {
    return this.friendService.declineRequest(nickname);
  }

  public removeFriend(nickname) {
    return this.friendService.removeFriend(nickname);
  }

  test(){
    console.log("test");
  }


}
