import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageOutComponent } from './message-out.component';

describe('MessageOutComponent', () => {
  let component: MessageOutComponent;
  let fixture: ComponentFixture<MessageOutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageOutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
