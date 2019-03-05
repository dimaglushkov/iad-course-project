import { Component, OnInit, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-site-layout',
  templateUrl: './site-layout.component.html',
  styleUrls: ['./site-layout.component.css']
})
export class SiteLayoutComponent implements OnInit {

  currentNickname: string;

  constructor(private router: Router,
    private httpClient: HttpClient,
    private userService: UserService) {
  }

  ngOnInit() {
    this.currentNickname = localStorage.getItem('curNickname');
  }

  logout() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate([''])
  }

}
