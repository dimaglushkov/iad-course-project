import { Component, OnInit, Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user: Object;  

  constructor(private route: ActivatedRoute, private router: Router) { 
     this.route.params.subscribe( params => this.user = params.nickname );
  }

  ngOnInit() {
    let username = <string> this.user;
    localStorage.setItem('curPage', username);
  }

}
