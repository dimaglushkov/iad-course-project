import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../shared/services/message.service';
import { MessageList } from '../interfaces';

@Component({
  selector: 'app-message-in',
  templateUrl: './message-in.component.html',
  styleUrls: ['./message-in.component.css']
})
export class MessageInComponent implements OnInit {

  currentNickname:string;
  tempNickname:string;
  //messageResponse: MessageList;

  constructor(private router: Router, private messageService: MessageService) { }

  ngOnInit() {
    this.tempNickname = 'testing';
    this.currentNickname = localStorage.getItem('curNickname');
    //  this.messageService.findInbox().subscribe(
    //    messageService => this.messageResponse = messageService
    //  );
    //  if (this.messageResponse.state.success == false){
    //    localStorage.setItem('lastError', this.messageResponse.state.description);
    //    this.router.navigate(['error']);
    //  }
  }
}
