import { Component, inject, Input } from '@angular/core';

import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzUploadChangeParam, NzUploadFile, NzUploadModule } from 'ng-zorro-antd/upload';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { getBase64, getBinary } from '../../utils/upload.utils';

@Component({
  selector: 'app-upload',
  imports: [NzUploadModule, NzIconModule, NzModalModule],
  templateUrl: './upload.component.html'
})
export class UploadComponent {
  @Input() class = "";
  @Input() fileList!: ArrayBuffer[];
  previewImage: string | undefined = '';
  previewVisible = false;

  private messageService = inject(NzMessageService);

  handlePreview = async (file: NzUploadFile): Promise<void> => {
    const url = await getBase64(file.originFileObj!);
    
    if(typeof url != "string") {
      console.error(url);
      return;
    }

    file.url =  url;
    this.previewImage = file.url;
    this.previewVisible = true;
  };

  async handleChange({file}: NzUploadChangeParam) {
    const binary = await getBinary(file.originFileObj!);

    if(!(binary instanceof ArrayBuffer)) {
      console.error(binary);
      return;
    }

    const binaryString = JSON.stringify([...new Uint8Array(binary)]);

    const alreadyExists = this.fileList.some(existingFile => 
      JSON.stringify([...new Uint8Array(existingFile)]) === binaryString
    );
    
    console.log(alreadyExists)
  
    if (!alreadyExists) {
      this.fileList.push(binary);
    }
  }
}
