import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-message-new',
  templateUrl: './message-new.component.html',
  styleUrls: ['./message-new.component.css']
})
export class MessageNewComponent implements OnInit {

  receiver: string;
  topic: string;
  text: string;

  constructor() { }

  ngOnInit() {
  }

  onSubmit(){
    if (this.receiver == null ||  this.receiver.length == 0)
      console.log('empty');

    else
      console.log(this.receiver);

      console.log(this.topic);
    console.log(this.text);

      
  }

}
