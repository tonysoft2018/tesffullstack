import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appDecimalNumberDirective]'
})
export class DecimalNumberDirectiveDirective {

  @Input('appDecimalNumber') decimalLimit: number = 2;

  constructor(private el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event: any) {
    const initialValue = this.el.nativeElement.value;
    const regex = new RegExp('^\\d*\\.?\\d{0,' + this.decimalLimit + '}$');

    if (regex.test(initialValue)) {
      return;
    } else {
      event.preventDefault();
      this.el.nativeElement.value = initialValue.substring(0, initialValue.length - 1);
    }
  }

}
