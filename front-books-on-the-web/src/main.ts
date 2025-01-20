import { bootstrapApplication } from '@angular/platform-browser';
import { RootComponent } from './app/root/root.component';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app/app.routes';

bootstrapApplication(RootComponent, {
  providers: [
    provideRouter(routes), // Configuração de rotas
    provideHttpClient(),   // Configuração do cliente HTTP
  ],
}).catch((err) => console.error(err));
