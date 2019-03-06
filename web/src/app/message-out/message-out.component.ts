import { Component, OnInit } from '@angular/core';
import { MessageList } from '../interfaces';
import { Router } from '@angular/router';
import { MessageService } from '../shared/services/message.service';

@Component({
  selector: 'app-message-out',
  templateUrl: './message-out.component.html',
  styleUrls: ['./message-out.component.css']
})
export class MessageOutComponent implements OnInit {

  currentNickname: string;
  messageResponse: MessageList;

  constructor(private router: Router, private messageService: MessageService) { }

  ngOnInit() {
    this.currentNickname = localStorage.getItem('curNickname');
    this.messageService.getOutbox().subscribe(
      messageService => this.messageResponse = messageService
    );
    if (this.messageResponse.state.success == false) {
      localStorage.setItem('lastError', this.messageResponse.state.description);
      this.router.navigate(['error']);
    }
  }

}
