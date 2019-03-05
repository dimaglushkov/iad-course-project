import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {

  description: string;

  constructor() { }

  ngOnInit() {
    this.description = localStorage.getItem('lastError');
  }

}
