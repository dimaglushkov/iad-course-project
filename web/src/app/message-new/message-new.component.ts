import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MessageService } from '../shared/services/message.service';
import { ResponseState } from '../interfaces';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Statement } from '@angular/compiler';

@Component({
  selector: 'app-message-new',
  templateUrl: './message-new.component.html',
  styleUrls: ['./message-new.component.css']
})
export class MessageNewComponent implements OnInit {

  receiver: string;
  topic: string;
  text: string;

  state: ResponseState;
  response: Object;


  constructor(private messageService: MessageService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.receiver == null || this.receiver.length == 0 || this.receiver == undefined ||
      this.topic == null || this.topic == undefined || this.topic.length == 0 ||
      this.text == null || this.text == undefined || this.text.length == 0 ||
      this.text.length > 500 || this.topic.length > 200 || this.receiver.length > 40) {
      alert('Корректно заполните все поля!');
      return false;
    }

    this.messageService.sendMessage(this.receiver, this.topic, this.text).subscribe(
      messageService => this.state = messageService
    );

    this.router.navigate(['/message/out']);

  }

}
