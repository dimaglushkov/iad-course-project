import { Component, OnInit, Injectable } from '@angular/core';
import { UserService } from '../shared/services/user.service'
import { Observable } from 'rxjs';
import { StringifyOptions } from 'querystring';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user:Object;

  constructor(private userService: UserService, private route: ActivatedRoute) { 
    this.route.params.subscribe(params => this.user = params.nickname);
  }
  ngOnInit() {
  }

}
