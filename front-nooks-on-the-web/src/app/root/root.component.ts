import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <h1>BooksOnTheWeb</h1>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./root.component.css'],
})
export class RootComponent {}
