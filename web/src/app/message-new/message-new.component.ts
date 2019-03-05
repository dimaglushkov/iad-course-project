import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MessageService } from '../shared/services/message.service';
import { ResponseState } from '../interfaces';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

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

  constructor(private messageService: MessageService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.receiver == null || this.receiver.length == 0 || this.receiver == undefined ||
      this.topic == null || this.topic == undefined || this.topic.length == 0 ||
      this.text == null || this.text == undefined || this.text.length == 0) {
      alert('Заполните все поля!');
    }

    this.messageService.sendMessage(this.receiver, this.topic, this.text).subscribe(
      messageService => this.state = messageService
    );

    alert(this.state.description)

    if (this.state.success == true)
    {
      this.router.navigate(['/welcome']);
    }

  }

}
