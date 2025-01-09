import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  template: `<h1>WebSocket Notification Example</h1>`,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'notification-ui';
  constructor(
    private webSocketService: AppService,
    private toastr: ToastrService
  ) {
    this.toastr.toastrConfig.positionClass = 'toast-top-center';
  }

  ngOnInit(): void {
    this.webSocketService.connect();
    this.listenForMessages();
  }

  listenForMessages(): void {
    this.webSocketService.getMessages().subscribe((message: string) => {
      if(message){
        if(message.includes("success")){
          this.toastr.success(message, 'Notification');
        } else if (message.includes("failure")){
          this.toastr.error(message, 'Notification');
        } else if (message.includes("warning")){
          this.toastr.warning(message, 'Notification');
        } else {
          this.toastr.info(message, 'Notification');
        }
      }

    });
  }
}
