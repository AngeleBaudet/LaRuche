import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'gelee',
})
export class GeleePipe implements PipeTransform {
  transform(value: any): string {
    if (value == 'Gelee_Royale') {
      return (value = 'Gelée Royale');
    } else return value;
  }
}
