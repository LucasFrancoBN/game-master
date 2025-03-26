import { Component, inject, Input, OnChanges, SimpleChanges } from '@angular/core';

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
export class UploadComponent implements OnChanges {
  @Input() class = "";
  @Input() fileList!: File[];
  nzFileList: NzUploadFile[] = [];
  previewImage: string | undefined = '';
  previewVisible = false;
  removeFiles = false;

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.fileList);
    if(this.fileList.length === 0)
      this.nzFileList = [];
  }

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
    if(!file.originFileObj) {
      console.error('File does not exists');
      return;
    }

    const fileExists = file.originFileObj;

    const alreadyExists = this.fileList.some(existingFile => 
      existingFile.name === fileExists.name
    );
  
    if (!alreadyExists) {
      this.fileList.push(fileExists);
    }
  }
}
