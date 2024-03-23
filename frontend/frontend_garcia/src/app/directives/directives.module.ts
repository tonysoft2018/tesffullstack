import { NgModule } from '@angular/core';
import { DecimalNumberDirectiveDirective } from './decimalNumberDirective.directive';

@NgModule({
  declarations: [DecimalNumberDirectiveDirective],
  exports:[DecimalNumberDirectiveDirective]
})
export class DirectivesModule { }
